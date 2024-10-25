package com.artist.service.impl;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artist.dto.response.BiddingHistoryDTO;
import com.artist.dto.response.BidrecordDTO;
import com.artist.dto.response.FinalBiddingList;
import com.artist.dto.response.PaintingDTO;
import com.artist.dto.response.TopBiddingsDTO;
import com.artist.dto.response.WalletDTO;
import com.artist.entity.Bidrecord;
import com.artist.entity.Customers;
import com.artist.repository.BidrecordRepository;
import com.artist.service.BidrecordService;

@Service
public class BidrecordServiceImpl implements BidrecordService {
	@Autowired
	BidrecordRepository brr;
	@Autowired
	PaintingsServiceImpl psi;
	@Autowired
	CustomersServiceImpl csi;
	@Lazy
	@Autowired
	OrdersServiceImpl osi;
	@Autowired
	EmailServiceImpl esi;

	@Value("${paintings.upload.date.totalday}")
	private int totalDay; // 讀取配置

	@Value("${paintings.upload.date.canbidday}")
	private int canBidDay; // 讀取配置
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


	@Override
	@Transactional
	public void bidding(String paintingId, String bidderId, Double bidAmount) {
		PaintingDTO paintings = psi.getByPaintingsId(paintingId);
		
			LocalDateTime bidTime = LocalDateTime.now();
			Boolean isWinningBid = true;
			Double deposit = bidAmount/10; //押金收10%
			Bidrecord bidrecord = new Bidrecord(paintingId, bidderId,"In Bidding", bidTime, bidAmount, isWinningBid, deposit, "pending" ,0.0);
			List<Bidrecord> binddinglist = brr.findByPaintingIdOrderByBidAmountDesc(paintingId);
			//查出底價
			Double price = paintings.getPrice();
			
			long delay = 0;
			long remiantime;
			if (bidAmount<=price) {
				 throw new RuntimeException("出價需大於底價");
			}else if(binddinglist.isEmpty()){
				brr.save(bidrecord);// 第一筆出價，直接存
				
				
//				並新增下架流程
				LocalDateTime uploadDate = paintings.getUploadDate();
				LocalDateTime removeDate = uploadDate.plusDays(14); // 這邊修改下架時間 plusDays plusHours plusMinutes
				// 計算現在時間和下架時間的時間差
				delay = Duration.between(LocalDateTime.now(), removeDate).toMillis();
				System.out.println("Scheduling removal task: " + paintings.getPaintingId() + "，延遲：" + delay + " 毫秒");

				scheduler.schedule(() -> {
					try {
						osi.finalizeHighestBidAsOrder(paintings, removeDate);
						psi.setSatusfinished(paintings.getPaintingId());
						System.out.println("商品已自動下架：" + paintings.getPaintingId());
					} catch (Exception e) {
						e.printStackTrace();
					}		
						esi.sendAuctionWinningEmail(paintings.getPaintingId());
				}, delay, TimeUnit.MILLISECONDS);
				
				//新增前一小時寄信通知
				remiantime = delay-86400000;//1天的毫秒數
				System.out.println("新增一個快結標前通知 removal task: " + paintings.getPaintingId() + "，延遲：" + remiantime + " 毫秒");
					scheduler.schedule(() -> {
						try {
							System.out.println(paintings.getPaintingId()+" 截標倒數24小時");
							 esi.sendAuctionRemiderEmail();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}, remiantime, TimeUnit.MILLISECONDS);
				
				
				// 出價有大於舊的最高
			} else if (bidAmount > (binddinglist.get(0).getBidAmount())) {
				brr.save(bidrecord);// 存入新的data
				
				// 抓出舊的最高價 isWinningBid -->改成false
				Bidrecord oldwinningBid = binddinglist.get(0);
				oldwinningBid.setIsWinningBid(false);						//設成不是最高
				oldwinningBid.setRefundAmount(oldwinningBid.getDeposit());	//紀錄退多少錢
				oldwinningBid.setRefundDate(LocalDateTime.now());			//紀錄時間
				oldwinningBid.setDepositStatus("refunded");					//更改狀態
				brr.save(oldwinningBid);// update之前最高的

				Customers customer = csi.getByCustomerId(oldwinningBid.getBidderId());
				Double bankBalance = customer.getBankBalance();
				bankBalance+=oldwinningBid.getDeposit();
				customer.setBankBalance(bankBalance);
				csi.update(customer);					//更改customer表的account值
				
				//新增前一小時寄信通知
				remiantime = delay-86400000;//1天的毫秒數
				System.out.println("新增一個快結標前通知 removal task: " + paintings.getPaintingId() + "，延遲：" + remiantime + " 毫秒");
					scheduler.schedule(() -> {
						try {
							System.out.println(paintings.getPaintingId()+" 截標倒數24小時");
							 esi.sendAuctionRemiderEmail();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}, remiantime, TimeUnit.MILLISECONDS);
				
			} else {
				 throw new RuntimeException("需高於最高價");
			}
		

	}

	@Override
	public List<BidrecordDTO> getAllBiddingHistoryByPaintings(String paintingId) {
		//用paintingId查出所有出價紀錄
		List<Bidrecord> binddinglist = brr.findByPaintingIdOrderByBidAmountDesc(paintingId);
		List<BidrecordDTO> bidrecordDTOList = new ArrayList<>();

		for(Bidrecord b: binddinglist) {
			BidrecordDTO bidrecordDTO = new BidrecordDTO();
			bidrecordDTO.setNickName(csi.getByCustomerId(b.getBidderId()).getNickName());//從bidrecord中拿到bidderId-->去查客戶nickname 設給DTO
			bidrecordDTO.setBidTime(b.getBidTime());
			bidrecordDTO.setBidAmount(b.getBidAmount());
			bidrecordDTOList.add(bidrecordDTO);
		}
		return bidrecordDTOList;
	}

	@Override
	public List<BiddingHistoryDTO> getAllBiddingHistoryBycustomerId(String bidderId, String nickname) {
		List<BiddingHistoryDTO> bidrecordDTOList = new ArrayList<>();
		List<Bidrecord> Bidderlist = brr.findByBidderIdOrderByBidTimeDesc(bidderId);

		for (Bidrecord b : Bidderlist) {

			PaintingDTO paintings = psi.getByPaintingsId(b.getPaintingId());
			paintings.getArtisName();
			paintings.getSmallUrl();

			BiddingHistoryDTO historyDTO = new BiddingHistoryDTO();
			historyDTO.setNickName(nickname);
			historyDTO.setPaintingId(b.getPaintingId());
			historyDTO.setBidAmount(b.getBidAmount());
			historyDTO.setBidTime(b.getBidTime());
			historyDTO.setPaintingName(paintings.getPaintingName());
			historyDTO.setArtisName(paintings.getArtisName());
			historyDTO.setSmallUrl(paintings.getSmallUrl());
			historyDTO.setStatus("競標中");
			bidrecordDTOList.add(historyDTO);
		}

		return bidrecordDTOList;
	}
	
	@Override
	public List<WalletDTO> getDepositRecord(String bidderId, String depositStatus){
	List<WalletDTO> walletDTOList = new ArrayList<>();
	List<Bidrecord> bidInfo = brr.findByBidderIdAndDepositStatusOrderByBidTime(bidderId, depositStatus);
	for(Bidrecord b:bidInfo) {
		WalletDTO walletDTO = new WalletDTO();
		walletDTO.setRefundDate(b.getRefundDate());
		walletDTO.setRefundAmount(b.getRefundAmount());
		walletDTOList.add(walletDTO);
		}
	
	return walletDTOList;
	}
	
	@Override
//	public List<TopBiddingsDTO> getTopBidding(int size) {
//        Pageable pageable = PageRequest.of(0, size);
//        List<Tuple> results = brr.findTopBiddingWithLimit(pageable);
//        return results.stream()
//                .map(tuple -> new TopBiddingsDTO(
//                        tuple.get("paintingId", String.class),
//                        tuple.get("paintingCount", Long.class)
//                ))
//                .collect(Collectors.toList());
//     
//	}
	public List<TopBiddingsDTO> getTopBidding(int size) {
	 
	    List<Object[]> results = brr.findTopBiddingWithLimit(totalDay, size); // 使用原生查詢

	    return results.stream()
	            .map(result -> new TopBiddingsDTO(
	                    (String) result[0],  // paintingId
	                    (Long) result[1]     // paintingCount
	            ))
	            .collect(Collectors.toList());
	}
	
	public List<FinalBiddingList> getFinalBiddingList() {
		 
	    List<Object[]> results = brr.findBidderForFinalBidding(totalDay); // 使用原生查詢
	    List<FinalBiddingList> finalBiddingList = new ArrayList<>();
	    
	    
	    //Object[] 中的每個元素的順序與 FinalBiddingList 類別的屬性相符
        for (Object[] result : results) {
            FinalBiddingList bidding = new FinalBiddingList();
            bidding.setPaintingId((String) result[0]); // 表單第1欄 paintingId
            bidding.setPaintingName((String) result[1]); // 表單第2欄 paintingName

            bidding.setBidderId((String) result[2]); // 第3欄是 bidderId
//            bidding.setBidLastTime((LocalDateTime) result[2]); // 第3欄是 bidLastTime
            
         // 將 result[2] 轉換為 Timestamp，然後轉換為 LocalDateTime
            Timestamp timestamp = (Timestamp) result[3];// 第4欄是 bidLastTime
            LocalDateTime bidLastTime = timestamp.toLocalDateTime();
            bidding.setBidLastTime(bidLastTime); 
            Timestamp timestamp2 = (Timestamp) result[4];// 第5欄是 bidLastTime
            LocalDateTime auctionClosedTime = timestamp2.toLocalDateTime();
            bidding.setAuctionClosedTime(auctionClosedTime); 
            bidding.setBidAmount((Double) result[5]); // 第6欄是 bidAmount
            bidding.setName((String) result[6]);// 第7欄是 name
            bidding.setEmail((String) result[7]);// 第8欄是 name
            bidding.setCurrentHighestBidAmount((Double) result[8]); // 第9欄是 currentHighestBidAmount

            finalBiddingList.add(bidding);
        }

        return finalBiddingList; 
	}
	
}
