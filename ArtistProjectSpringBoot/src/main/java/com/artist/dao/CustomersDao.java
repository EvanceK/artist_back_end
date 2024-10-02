package com.artist.dao;

import java.util.List;

import com.artist.entity.Customers;

public interface CustomersDao {
	// Create
	void create(Customers c);

	// Read
	List<Customers> selectAll();

	// Update
	void update(String id,String lastname,String firstname,String email,String account,String password);

	// Delete
	void delete(String id);
}
