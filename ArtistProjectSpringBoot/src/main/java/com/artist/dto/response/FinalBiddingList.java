package com.artist.dto.response;

import java.time.LocalDateTime;

public class FinalBiddingList {
	private String paintingId ;
	private String bidderId;
	private LocalDateTime bidLastTime;
	private Double bidAmount;
	private Double currentHighestBidAmount;
	public FinalBiddingList() {
		super();
	}
	public String getPaintingId() {
		return paintingId;
	}
	public void setPaintingId(String paintingId) {
		this.paintingId = paintingId;
	}
	public String getBidderId() {
		return bidderId;
	}
	public void setBidderId(String bidderId) {
		this.bidderId = bidderId;
	}
	public LocalDateTime getBidLastTime() {
		return bidLastTime;
	}
	public void setBidLastTime(LocalDateTime bidLastTime) {
		this.bidLastTime = bidLastTime;
	}
	public Double getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(Double bidAmount) {
		this.bidAmount = bidAmount;
	}
	public Double getCurrentHighestBidAmount() {
		return currentHighestBidAmount;
	}
	public void setCurrentHighestBidAmount(Double currentHighestBidAmount) {
		this.currentHighestBidAmount = currentHighestBidAmount;
	}
	

	
}
