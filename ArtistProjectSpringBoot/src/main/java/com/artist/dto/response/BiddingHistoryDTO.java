package com.artist.dto.response;

import java.time.LocalDateTime;

public class BiddingHistoryDTO {

	private String nickName; // from customers
	private String paintingId;
	private String paintingName;
	private String artisName;
	private String smallUrl;
	private LocalDateTime bidTime;
	private Double bidAmount;

	public BiddingHistoryDTO() {
		super();
	}

	public BiddingHistoryDTO(String nickName, String bidderId, String paintingId, String paintingName, String artisName,
			String smallUrl, LocalDateTime bidTime, Double bidAmount) {
		super();
		this.nickName = nickName;
		this.paintingId = paintingId;
		this.paintingName = paintingName;
		this.artisName = artisName;
		this.smallUrl = smallUrl;
		this.bidTime = bidTime;
		this.bidAmount = bidAmount;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getPaintingId() {
		return paintingId;
	}

	public void setPaintingId(String paintingId) {
		this.paintingId = paintingId;
	}

	public String getPaintingName() {
		return paintingName;
	}

	public void setPaintingName(String paintingName) {
		this.paintingName = paintingName;
	}

	public String getArtisName() {
		return artisName;
	}

	public void setArtisName(String artisName) {
		this.artisName = artisName;
	}

	public String getSmallUrl() {
		return smallUrl;
	}

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

}
