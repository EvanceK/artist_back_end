package com.artist.dto;

import java.time.LocalDateTime;

public class BidrecordDTO {
	
	private String nickName;  //from customers
	private String bidderId; //等於customerId
	private LocalDateTime bidTime;
	private Double bidAmount;
	
	
	public BidrecordDTO() {
		super();
	}


	public BidrecordDTO(String nickName, String bidderId, LocalDateTime bidTime, Double bidAmount) {
		super();
		this.nickName = nickName;
		this.bidderId = bidderId;
		this.bidTime = bidTime;
		this.bidAmount = bidAmount;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getBidderId() {
		return bidderId;
	}


	public void setBidderId(String bidderId) {
		this.bidderId = bidderId;
	}


	public LocalDateTime getBidTime() {
		return bidTime;
	}


	public void setBidTime(LocalDateTime bidTime) {
		this.bidTime = bidTime;
	}


	public Double getBidAmount() {
		return bidAmount;
	}


	public void setBidAmount(Double bidAmount) {
		this.bidAmount = bidAmount;
	}


}
