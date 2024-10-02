package com.artist.bean;

import java.time.LocalDateTime;

public class Inventories {
	private String inventoryNumber;
	private LocalDateTime inventoryDate;

	public Inventories() {
	}

	public Inventories(String inventoryNumber, LocalDateTime inventoryDate) {
		this.inventoryNumber = inventoryNumber;
		this.inventoryDate = inventoryDate;
	}

	public String getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(String inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}

	public LocalDateTime getInventoryDate() {
		return inventoryDate;
	}

	public void setInventoryDate(LocalDateTime inventoryDate) {
		this.inventoryDate = inventoryDate;
	}
}
