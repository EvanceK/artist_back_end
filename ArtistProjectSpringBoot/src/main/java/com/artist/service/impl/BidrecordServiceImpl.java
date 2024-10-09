package com.artist.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artist.dto.BidrecordDTO;
import com.artist.dto.PaintingDTO;
import com.artist.entity.Bidrecord;
import com.artist.repository.BidrecordRepository;
import com.artist.service.BidrecordService;
@Service
public class BidrecordServiceImpl implements BidrecordService{
	@Autowired
	BidrecordRepository brr;
	@Autowired
	PaintingsServiceImpl psi;
	@Autowired
	CustomersServiceImpl csi;
	@Override
	@Transactional
	public void bidding(String paintingId, String bidderId,Double bidAmount) {
		
		LocalDateTime bidTime = LocalDateTime.now();
		Boolean isWinningBid = true;
		Bidrecord bidrecord = new Bidrecord(paintingId, bidderId, bidTime, bidAmount,
					isWinningBid);
		List<Bidrecord> binddinglist = brr.findByPaintingIdOrderByBidAmountDesc(paintingId);
		
		if ( binddinglist.isEmpty()) {
			brr.save(bidrecord);//第一筆出價，直接存
		//出價有大於舊的最高
		}else if(bidAmount>(binddinglist.get(0).getBidAmount())){
			// 存入新的data
			brr.save(bidrecord);
			Bidrecord oldwinningBid = binddinglist.get(0);
			//抓出舊的最高價 isWinningBid -->改成false
			oldwinningBid.setIsWinningBid(false);
			brr.save(oldwinningBid);//update之前最高的
		}else {
			System.out.println("出價邏輯異常");

		}
		
	}

	@Override
	public List<BidrecordDTO> getAllBiddingHistoryByPaintings(String paintingId,String bidderId,Double bidAmount) {
		List<Bidrecord> binddinglist = brr.findByPaintingIdOrderByBidAmountDesc(paintingId);
		PaintingDTO paintingsId = psi.getByPaintingsId(paintingId);
		
		
//		BidrecordDTO bidrecordDTO = new BidrecordDTO();
//			
//		bidrecordDTO.setNickName(cs);
//		bidrecordDTO.setBidderId(paintingsId.getPaintingName());
//		bidrecordDTO.setBidTime(paintingsId.getPaintingName());
//		bidrecordDTO.setBidAmount(bidAmount);

		return null;
	}
	
	
	private String largUrl;
	private String smallUrl;
	private String date;
	private String style;
	private LocalDateTime uploadDate;
	private String genre;
	private String status;
	private String nickName;  //from customers
	private String bidderId; //等於customerId
	private LocalDateTime bidTime;
	private Double bidAmount;
	

	@Override
	public List<BidrecordDTO> getAllBiddingHistoryBycustomerId(String customerId) {
		return null;
	}



}
