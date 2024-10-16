package com.artist.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.artist.dto.response.PaintingDTO;
import com.artist.dto.response.WinningRecords;

public interface OrdersService {
	// Create
	public String create(LocalDateTime orderDate,String customerId, String status);

    // Read
    List<WinningRecords> getAllWinningRecordsByCustomerId(String customerId);



    // Update
 
    // Delete
	
	
	//自動下架用
	public void finalizeHighestBidAsOrder(PaintingDTO painting, LocalDateTime removeDate);

}
