package com.artist.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "orders")
public class Orders {

	
	@Id
	@Column(name = "order_number")
	private String orderNumber;
	
	@Column(name = "order_date")
	private LocalDateTime orderDate;
	
	@Column(name = "customer_id")
    private String customerId;
	
	@Column(name = "service_fee")
	private int serviceFee;
	
	@Column(name = "desposit")
	private int desposit;
	
	@Column(name = "total_amount")
	private int totalAmount;
	
	@Column(name = "delivery_number")
	private String deliveryNumber;
	
    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonBackReference
    private OrderDetails orderDetail;
	

	//與Customers關聯
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customers customer;

	public Orders() {
		super();
	}

	public Orders(LocalDateTime orderDate, String customerId, int serviceFee, int desposit, int totalAmount,
			String deliveryNumber, OrderDetails orderDetail, Customers customer) {
		super();
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.serviceFee = serviceFee;
		this.desposit = desposit;
		this.totalAmount = totalAmount;
		this.deliveryNumber = deliveryNumber;
		this.orderDetail = orderDetail;
		this.customer = customer;
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

	public OrderDetails getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetails orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public String getDeliveryNumber() {
		return deliveryNumber;
	}

	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
    
}
