package com.artist.dto.response;

import java.time.LocalDateTime;
import java.util.Arrays;

public class MyOrderResponse {
	
	private String customerId;
	private String deliveryNumber;
	private LocalDateTime createDate;
	private String status;
	private String attName;
	private String deliveryAddress;
	private String deliveryInstrictions;
	private Integer totalAmount;
	private String paintingId;
	private String paintingName;
	private String artistName;
	private byte[] image;
	public MyOrderResponse() {
		super();
	}
	public MyOrderResponse(String customerId, String deliveryNumber, LocalDateTime createDate, String status,
			String attName, String deliveryAddress, String deliveryInstrictions, Integer totalAmount, String paintingId,
			String paintingName, String artistName, byte[] image) {
		super();
		this.customerId = customerId;
		this.deliveryNumber = deliveryNumber;
		this.createDate = createDate;
		this.status = status;
		this.attName = attName;
		this.deliveryAddress = deliveryAddress;
		this.deliveryInstrictions = deliveryInstrictions;
		this.totalAmount = totalAmount;
		this.paintingId = paintingId;
		this.paintingName = paintingName;
		this.artistName = artistName;
		this.image = image;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getDeliveryNumber() {
		return deliveryNumber;
	}
	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAttName() {
		return attName;
	}
	public void setAttName(String attName) {
		this.attName = attName;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getDeliveryInstrictions() {
		return deliveryInstrictions;
	}
	public void setDeliveryInstrictions(String deliveryInstrictions) {
		this.deliveryInstrictions = deliveryInstrictions;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
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
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "MyOrderResponse [customerId=" + customerId + ", deliveryNumber=" + deliveryNumber + ", createDate="
				+ createDate + ", status=" + status + ", attName=" + attName + ", deliveryAddress=" + deliveryAddress
				+ ", deliveryInstrictions=" + deliveryInstrictions + ", totalAmount=" + totalAmount + ", paintingId="
				+ paintingId + ", paintingName=" + paintingName + ", artistName=" + artistName + ", image="
				+ Arrays.toString(image) + "]";
	}

}
