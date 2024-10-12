package com.artist.service;

import java.time.LocalDateTime;

import com.artist.dto.response.PaintingDTO;

public interface OrderDetailsService {
	// Create
	void create(String paintingId,Double bidAmount);
	void finalizeHighestBidAsOrder(PaintingDTO painting, LocalDateTime removeDate);

    // Read
   
    // Update
 
    // Delete
}
