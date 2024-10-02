package com.artist.dao;

import java.util.List;

import com.artist.entity.InventoryDetails;

public interface InventoryDetailsDao {

	//Create
	void create(String inventoryNumber, String paintingId);
	//Read
	List<InventoryDetails> selectAll();
	//Update
	void update(InventoryDetails preInv, InventoryDetails newInv);
	//Delete
	void delete(String inventoryNumber, String paintingId);
}
