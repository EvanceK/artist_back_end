package com.artist.service;

import java.util.List;

import com.artist.dto.response.BiddingHistoryDTO;
import com.artist.dto.response.BidrecordDTO;
import com.artist.dto.response.WalletDTO;

public interface BidrecordService {

	//出價
	void bidding(String paintingId, String bidderId,Double bidAmount);
	//查詢所有出價紀錄By painting
	public List<BidrecordDTO> getAllBiddingHistoryByPaintings(String paintingId);

	//查詢所有出價紀錄By Customer --> Bidding History
	List<BiddingHistoryDTO> getAllBiddingHistoryBycustomerId(String bidderId ,String nickname);

	//for my wallet information 退款紀錄
	public List<WalletDTO> getDepositRecord(String bidderId, String depositStatus);


}
