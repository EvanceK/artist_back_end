package com.artist.service;

import java.time.LocalDateTime;

import com.artist.dto.response.PaintingDTO;

public interface OrdersService {
	// Create
	public String create(LocalDateTime orderDate,String customerId, String status);

    // Read
   
    // Update
 
    // Delete
	
	public void finalizeHighestBidAsOrder(PaintingDTO painting, LocalDateTime removeDate);

}
