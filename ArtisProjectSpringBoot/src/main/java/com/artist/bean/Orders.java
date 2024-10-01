package com.artist.bean;

import java.time.LocalDateTime;

public class Orders {

	private String orderNumber;
	private LocalDateTime orderDate;
	private String customerId;

	public Orders() {
	}

	public Orders(String orderNumber, LocalDateTime orderDate, String customerId) {
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.customerId = customerId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
