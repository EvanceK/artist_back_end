package com.artist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {
	
	
	@Id
	@Column(name = "order_number")
	private String orderNumber;
	
	@Column(name = "painting_id")
	private String paintingId;
	
	@Column(name = "price")
	private Double price;

//	//與Orders關聯
//    @ManyToOne
//    @JoinColumn(name = "order_number", insertable = false, updatable = false)
//    private Customers customer;
	
	public OrderDetails() {
	}

	public OrderDetails(String orderNumber, String paintingId, Double price) {
		this.orderNumber = orderNumber;
		this.paintingId = paintingId;
		this.price = price;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPaintingId() {
		return paintingId;
	}

	public void setPaintingId(String paintingId) {
		this.paintingId = paintingId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
