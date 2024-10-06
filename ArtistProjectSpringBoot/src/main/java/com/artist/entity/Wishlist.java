package com.artist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "wishlist")
public class Wishlist {
	
	//與Customers關聯
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;
	
	@Column(name = "customer_id", insertable = false, updatable = false)
	private String customerId;
	@Id
	@Column(name = "painting_id")
	private String paintingId;
	
	public Wishlist() {
		super();
	}


	public Wishlist(String customerId, String paintingId) {
		super();
		this.customerId = customerId;
		this.paintingId = paintingId;
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


	public Customers getCustomer() {
		return customer;
	}


	public void setCustomer(Customers customer) {
		this.customer = customer;
	}


	@Override
	public String toString() {
		return "Wishlist [customerId=" + customerId + ", paintingId=" + paintingId + "]";
	}

	
	
}
