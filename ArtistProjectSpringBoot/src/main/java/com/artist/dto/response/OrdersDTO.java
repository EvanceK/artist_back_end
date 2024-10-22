package com.artist.dto.response;

import java.time.LocalDateTime;

public class OrdersDTO {

	private String orderNumber;
	private String customerId;
	private int serviceFee;
	private int desposit;
	private int totalAmount;
	private LocalDateTime orderDate;

	public OrdersDTO() {
		super();
	}

	public OrdersDTO(String orderNumber, String customerId, int serviceFee, int desposit, int totalAmount,
			LocalDateTime orderDate) {
		super();
		this.orderNumber = orderNumber;
		this.customerId = customerId;
		this.serviceFee = serviceFee;
		this.desposit = desposit;
		this.totalAmount = totalAmount;
		this.orderDate = orderDate;
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

	public int getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(int serviceFee) {
		this.serviceFee = serviceFee;
	}

	public int getDesposit() {
		return desposit;
	}

	public void setDesposit(int desposit) {
		this.desposit = desposit;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	
}
