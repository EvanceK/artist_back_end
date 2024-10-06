package com.artist.service;

import com.artist.dto.CustomersDTO;
import com.artist.entity.Customers;

public interface CustomersService {

	// Create
	void create(CustomersDTO customersDTO);

    // Read
	public String login(String email ,String password);
	public Customers getCustomer(String email);
    public String getCustomerIdFromToken(String token);


    // Update
    void update(CustomersDTO customersDTO);
    // Delete
    void delete(Customers Customers);
    void deleteByEmail(String email);
    


}
