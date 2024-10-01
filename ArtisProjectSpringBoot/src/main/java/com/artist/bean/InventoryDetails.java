package com.artist.bean;

public class InventoryDetails {
	
	private String inventoryNumber;
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
