package com.artist.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryOrderResponseDTO {
	
	    private String deliveryNumber; // 配送單號
	    private LocalDateTime createDate; // 出貨單創建日期
	    private String status; // 出貨狀態
	    private String attName; // 收件人姓名
	    private String attPhone; // 收件人電話
	    private String deliveryAddress; // 配送地址
	    private String deliveryInstrictions; // 配送指示
	    private Integer deliveryFee; // 配送費用
	    private Integer totalAmount; // 總金額
	    private String packageStaff; // 包裝人員ID
	    private String deliveryStaff; // 配送人員ID
	    private List<OrdersDTO> orderList; // 訂單列表

	    // Getters and Setters

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

	    public Integer getDeliveryFee() {
	        return deliveryFee;
	    }

	    public void setDeliveryFee(Integer deliveryFee) {
	        this.deliveryFee = deliveryFee;
	    }

	    public Integer getTotalAmount() {
	        return totalAmount;
	    }

	    public void setTotalAmount(Integer totalAmount) {
	        this.totalAmount = totalAmount;
	    }

	    public String getPackageStaff() {
	        return packageStaff;
	    }

	    public void setPackageStaff(String packageStaff) {
	        this.packageStaff = packageStaff;
	    }

	    public String getDeliveryStaff() {
	        return deliveryStaff;
	    }

	    public void setDeliveryStaff(String deliveryStaff) {
	        this.deliveryStaff = deliveryStaff;
	    }

	    public List<OrdersDTO> getOrderList() {
	        return orderList;
	    }

	    public void setOrderList(List<OrdersDTO> orderList) {
	        this.orderList = orderList;
	    }

		public DeliveryOrderResponseDTO(String deliveryNumber, LocalDateTime createDate, String status, String attName,
				String attPhone, String deliveryAddress, String deliveryInstrictions, Integer deliveryFee,
				Integer totalAmount, String packageStaff, String deliveryStaff, List<OrdersDTO> orderList) {
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
			this.orderList = orderList;
		}

		public DeliveryOrderResponseDTO() {
			super();
		}
	    
	}



