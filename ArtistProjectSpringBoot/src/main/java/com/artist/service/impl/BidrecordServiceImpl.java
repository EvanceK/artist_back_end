package com.artist.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artist.dto.response.BiddingHistoryDTO;
import com.artist.dto.response.BidrecordDTO;
import com.artist.dto.response.PaintingDTO;
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
	

	@Override
	@Transactional
	public void bidding(String paintingId, String bidderId, Double bidAmount) {

		LocalDateTime bidTime = LocalDateTime.now();
		Boolean isWinningBid = true;
		Double deposit = bidAmount/10; //押金收10%
		Bidrecord bidrecord = new Bidrecord(paintingId, bidderId, bidTime, bidAmount, isWinningBid, deposit, "pending" ,0.0);
		List<Bidrecord> binddinglist = brr.findByPaintingIdOrderByBidAmountDesc(paintingId);
		
		if (binddinglist.isEmpty()) {
			brr.save(bidrecord);// 第一筆出價，直接存
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
			System.out.println("出價邏輯異常");

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
			bidrecordDTOList.add(historyDTO);
		}

		return bidrecordDTOList;
	}

}
