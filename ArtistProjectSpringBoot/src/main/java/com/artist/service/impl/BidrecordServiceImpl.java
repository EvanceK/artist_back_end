package com.artist.service.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artist.dto.response.BiddingHistoryDTO;
import com.artist.dto.response.BidrecordDTO;
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
		
		if(paintings.getDelicated()==2) {
			throw new RuntimeException("尚未開放競價");
			
		}else {
			LocalDateTime bidTime = LocalDateTime.now();
			Boolean isWinningBid = true;
			Double deposit = bidAmount/10; //押金收10%
			Bidrecord bidrecord = new Bidrecord(paintingId, bidderId,"In Bidding", bidTime, bidAmount, isWinningBid, deposit, "pending" ,0.0);
			List<Bidrecord> binddinglist = brr.findByPaintingIdOrderByBidAmountDesc(paintingId);
			//查出底價
			Double price = paintings.getPrice();
			if (bidAmount<=price) {
				 throw new RuntimeException("出價需大於底價");
			}else if(binddinglist.isEmpty()){
				brr.save(bidrecord);// 第一筆出價，直接存
				
				
//				並新增下架流程
				
				LocalDateTime uploadDate = paintings.getUploadDate();
				LocalDateTime removeDate = uploadDate.plusDays(14); // 這邊修改下架時間 plusDays plusHours plusMinutes
				// 計算現在時間和下架時間的時間差
				long delay = Duration.between(LocalDateTime.now(), removeDate).toMillis();
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
			} else {
				 throw new RuntimeException("需高於最高價");
			}
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
}
