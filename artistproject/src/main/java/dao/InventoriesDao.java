package dao;

import java.util.List;

import bean.Inventories;

public interface InventoriesDao {

	// Create
	void create();

	// Read
	List<Inventories> selectAll();

	// Update
	void update(Inventories inv);

	// Delete
	void delete(String id);
}