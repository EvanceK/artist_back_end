package com.artist.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@Column(name = "order_number")
	private String orderNumber;
	
	@Column(name = "order_date")
	private LocalDateTime orderDate;
	
	@Column(name = "order_id")
	private String customerId;
	
	@Column(name = "delivery_adress")
	private String deliveryAddress;
	
	@Column(name = "att_name")
	private String attName;
	
	@Column(name = "att_phone")
	private String attPhone;

	@Column(name = "delivery_instrictions")
	private String deliveryInstrictions;
	
//	//與Customers關聯
//    @ManyToOne
//    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
//    private Customers customer;
//	
//	// 與 OrderDetails 的關聯
//    @OneToMany(mappedBy = "orders")
	
//    private List<OrderDetails> OrderDetails;
    
    
	public Orders() {
	}

	public Orders(String orderNumber, LocalDateTime orderDate, String customerId) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.customerId = customerId;
	}

	public Orders(String orderNumber, LocalDateTime orderDate, String customerId, String deliveryAddress,
			String attName, String attPhone, String deliveryInstrictions) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.deliveryAddress = deliveryAddress;
		this.attName = attName;
		this.attPhone = attPhone;
		this.deliveryInstrictions = deliveryInstrictions;
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



	public String getDeliveryAddress() {
		return deliveryAddress;
	}



	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
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

	public String getDeliveryInstrictions() {
		return deliveryInstrictions;
	}

	public void setDeliveryInstrictions(String deliveryInstrictions) {
		this.deliveryInstrictions = deliveryInstrictions;
	}
	
}
