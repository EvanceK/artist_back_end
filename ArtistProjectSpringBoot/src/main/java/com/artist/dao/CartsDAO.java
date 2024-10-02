package com.artist.dao;

import java.util.List;

import com.artist.entity.Carts;

public interface CartsDAO {
	// Create
	void create(Carts c);

	// Read
	List<Carts> selectAll();

	// Update
	void update(String cart_id,String painting_id);

	// Delete
	void delete(String id);
}
