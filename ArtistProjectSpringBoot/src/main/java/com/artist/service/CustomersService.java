package com.artist.service;

import com.artist.dto.CustomersDTO;
import com.artist.entity.Customers;

public interface CustomersService {

	// Create
	void create(CustomersDTO customersDTO);

    // Read
	String login(String email ,String password);
	Customers getCustomer(String email);
	Customers getByCustomerId(String customerId);
    String getCustomerIdFromToken(String token);
    String refreshToken(String token);

    // Update
    void update(String CustomerId, String pwd, String name, 
    		    String nickName, String phone, String address);
    // Delete
    void delete(Customers Customers);
    void deleteByEmail(String email);

}
