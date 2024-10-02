package com.artist.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventories")
public class Inventories {

	@Id
	@Column(name = "inventory_number")
	private String inventoryNumber;

	@Column(name = "inventory_date")
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
