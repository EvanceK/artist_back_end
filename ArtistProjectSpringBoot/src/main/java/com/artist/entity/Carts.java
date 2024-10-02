package com.artist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Carts {

	@Id
	@Column(name = "customer_id")
	private String customerId;
	
	@Column(name = "painting_id")
	private String paintingId;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "status")
	private String status;
	
	public Carts() {
	}

	public Carts(String customerId, String paintingId) {
		this.customerId = customerId;
		this.paintingId = paintingId;
	}


	public Carts(String customerId, String paintingId, Double price) {
		super();
		this.customerId = customerId;
		this.paintingId = paintingId;
		this.price = price;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}




}
