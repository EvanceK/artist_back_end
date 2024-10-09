package com.artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artist.dto.BiddingRequest;
import com.artist.dto.BidrecordDTO;
import com.artist.dto.WishlistDTO;
import com.artist.service.impl.BidrecordServiceImpl;
import com.artist.service.impl.CustomersServiceImpl;
import com.artist.service.impl.PaintingsServiceImpl;

@RestController
@RequestMapping("/api/bidding")
public class BidrecordController {
	
	
	@Autowired
	private PaintingsServiceImpl psi;
	@Autowired
	private CustomersServiceImpl csi;
	@Autowired
	private BidrecordServiceImpl bsi;
	
	@PostMapping
	public ResponseEntity<Void> bidding(@RequestHeader("Authorization") String token,
			@RequestBody BiddingRequest request) {
		String bidderId = csi.getCustomerIdFromToken(token);
		String paintingId = request.getPaintingId();
		Double bidAmount = request.getBidAmount();

		bsi.bidding(paintingId, bidderId, bidAmount);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@GetMapping("/history")
	public ResponseEntity<List<BidrecordDTO>> getBidHistory(@RequestHeader("Authorization") String token) {
	    String bidId = csi.getCustomerIdFromToken(token);
	    List<BidrecordDTO> biddingHistory = bsi.getAllBiddingHistoryBycustomerId(bidId);
	    
	    List<BidrecordDTO> allWishlistWithPaintings = null; // 此行需根據實際邏輯賦值
	    return ResponseEntity.ok(allWishlistWithPaintings); // 假設這裡需要返回ResponseEntity
	}
	
	// 查詢用戶所有biddinghistory的方法
	@GetMapping("/historssy")
	public ResponseEntity<List<BidlistDTO>> getAuctionBiddingHistory(@RequestHeader("Authorization") String token) {
		String customerId = csi.getCustomerIdFromToken(token);
		List<WishlistDTO> allWishlistWithPaintings = wsi.findAllWishlistWithPaintings(customerId);
		return ResponseEntity.ok(allWishlistWithPaintings);
	}
	
}
