package com.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, String>{

	
//	  Optional<Orders> findByCustomer_CustomerId(String customerId);

}
