package com.artist.dto.response;

import java.time.LocalDateTime;

public class OrdersDTO {

	private String orderNumber; // 訂單編號
    private LocalDateTime orderDate; // 訂單日期
    private String customerId; // 客戶ID
    private Integer serviceFee; // 服務費
    private Integer deposit; // 訂金
    private Integer totalAmount; // 總金額
    
	public OrdersDTO() {
		super();
	}
	
	
	public OrdersDTO(String orderNumber, LocalDateTime orderDate, String customerId, 
            Integer serviceFee, Integer deposit, Integer totalAmount) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.serviceFee = serviceFee;
		this.deposit = deposit;
		this.totalAmount = totalAmount;
	}
	// Getters and Setters
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

    public Integer getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Integer serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
}
	

