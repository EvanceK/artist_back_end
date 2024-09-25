package dao;

import java.util.List;

import bean.InventoryDetails;

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
