package com.artist.dto.response;

import java.time.LocalDateTime;

public class OrdersDTO {

	private String orderNumber;
	private String customerId;
	private String status;
	private String attName;
	private String attPhone;
	private String deliveryAdress;
	private LocalDateTime orderDate;
	private String deliveryInstrictions;
	public OrdersDTO() {
		super();
	}
	public OrdersDTO(String orderNumber, String customerId, 
			String status, String attName, String attPhone,
			String deliveryAdress, LocalDateTime orderDate,
			String deliveryInstrictions) {
		super();
		this.orderNumber = orderNumber;
		this.customerId = customerId;
		this.status = status;
		this.attName = attName;
		this.attPhone = attPhone;
		this.deliveryAdress = deliveryAdress;
		this.orderDate = orderDate;
		this.deliveryInstrictions = deliveryInstrictions;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	public String getAttPhone() {
		return attPhone;
	}
	public void setAttPhone(String attPhone) {
		this.attPhone = attPhone;
	}
	public String getDeliveryAdress() {
		return deliveryAdress;
	}
	public void setDeliveryAdress(String deliveryAdress) {
		this.deliveryAdress = deliveryAdress;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public String getDeliveryInstrictions() {
		return deliveryInstrictions;
	}
	public void setDeliveryInstrictions(String deliveryInstrictions) {
		this.deliveryInstrictions = deliveryInstrictions;
	}
	
}
