package com.artist.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "deliveryorders")
public class DeliveryOrders {

	@Id
	@Column(name = "delivery_number")
	private String deliveryNumber;
	
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "att_name")
	private String attName;
	
	@Column(name = "att_phone")
	private String attPhone;
	
	@Column(name = "delivery_address")
	private String deliveryAddress;
	
	@Column(name = "delivery_instrictions")
	private String deliveryInstrictions;
	
	@Column(name = "delivery_fee")
	private int deliveryFee;
	
	@Column(name = "total_amount")
	private int totalAmount;
	
	@Column(name = "package_staff")
	private int packageStaff;
	
	@Column(name = "delivery_staff")
	private int deliveryStaff;

	public DeliveryOrders() {
		super();
	}


	public DeliveryOrders(String deliveryNumber, LocalDateTime createDate, String status, String attName,
			String attPhone, String deliveryAddress, String deliveryInstrictions, int deliveryFee, int totalAmount,
			int packageStaff, int deliveryStaff) {
		super();
		this.deliveryNumber = deliveryNumber;
		this.createDate = createDate;
		this.status = status;
		this.attName = attName;
		this.attPhone = attPhone;
		this.deliveryAddress = deliveryAddress;
		this.deliveryInstrictions = deliveryInstrictions;
		this.deliveryFee = deliveryFee;
		this.totalAmount = totalAmount;
		this.packageStaff = packageStaff;
		this.deliveryStaff = deliveryStaff;
	}


	public String getDeliveryNumber() {
		return deliveryNumber;
	}

	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
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

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryInstrictions() {
		return deliveryInstrictions;
	}

	public void setDeliveryInstrictions(String deliveryInstrictions) {
		this.deliveryInstrictions = deliveryInstrictions;
	}

	public int getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getPackageStaff() {
		return packageStaff;
	}

	public void setPackageStaff(int packageStaff) {
		this.packageStaff = packageStaff;
	}

	public int getDeliveryStaff() {
		return deliveryStaff;
	}

	public void setDeliveryStaff(int deliveryStaff) {
		this.deliveryStaff = deliveryStaff;
	}
		
}
