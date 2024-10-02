package com.artist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventories")
public class InventoryDetails {
	
	@Id
	@Column(name = "inventory_number")
	private String inventoryNumber;
	
	@Column(name = "painting_id")
	private String paintingId;

	public InventoryDetails() {
	}

	public InventoryDetails(String inventoryNumber, String paintingId) {
		this.inventoryNumber = inventoryNumber;
		this.paintingId = paintingId;
	}

	public String getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(String inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}

	public String getPaintingId() {
		return paintingId;
	}

	public void setPaintingId(String paintingId) {
		this.paintingId = paintingId;
	}
}
