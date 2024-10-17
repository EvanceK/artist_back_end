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
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "att_name")
	private String attName;
	


	@Column(name = "att_phone")
	private String attPhone;

	@Column(name = "delivery_address")
	private String deliveryAdress;
	
	@Column(name = "delivery_instrictions")
	private String deliveryInstrictions;
	
	
    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonBackReference
    private OrderDetails orderDetail;
	



	//與Customers關聯
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customers customer;

	public Orders() {
		super();
	}


	public Orders(String orderNumber, LocalDateTime orderDate, String status, String attName, String attPhone,
			String deliveryAdress, String deliveryInstrictions, Customers customer) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.status = status;
		this.attName = attName;
		this.attPhone = attPhone;
		this.deliveryAdress = deliveryAdress;
		this.deliveryInstrictions = deliveryInstrictions;
		this.customer = customer;
	}



	public String getOrderNumber() {
		return orderNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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



	public String getDeliveryInstrictions() {
		return deliveryInstrictions;
	}



	public void setDeliveryInstrictions(String deliveryInstrictions) {
		this.deliveryInstrictions = deliveryInstrictions;
	}



	public Customers getCustomer() {
		return customer;
	}



	public void setCustomer(Customers customer) {
		this.customer = customer;
	}
    
    
	public OrderDetails getOrderDetail() {
		return orderDetail;
	}


	public void setOrderDetail(OrderDetails orderDetail) {
		this.orderDetail = orderDetail;
	}

    
}
