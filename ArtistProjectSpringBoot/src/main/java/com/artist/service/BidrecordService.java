package com.artist.service;

import java.util.List;

import com.artist.dto.BidrecordDTO;

public interface BidrecordService {

	//出價
	void bidding(String paintingId, String bidderId,Double bidAmount);
	//查詢所有出價紀錄By painting
	List<BidrecordDTO> getAllBiddingHistoryByPaintings(String bidderId,String paintingId,Double bidAmount);

	//查詢所有出價紀錄By Customer --> Bidding History
	List<BidrecordDTO> getAllBiddingHistoryBycustomerId(String customerId);

	//截標後移除所有紀錄by painting -->最高價到order Table 增加一筆 order
	//標寄mail?告知所有投過標最終的結果?--> 有無得標
}
