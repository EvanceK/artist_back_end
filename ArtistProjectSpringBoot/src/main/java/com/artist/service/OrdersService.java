package com.artist.service;

import java.time.LocalDateTime;
import java.util.List;

import com.artist.dto.request.RecipientInformation;
import com.artist.dto.response.PaintingDTO;
import com.artist.dto.response.WinningRecords;
import com.artist.entity.Orders;

public interface OrdersService {
	// Create
	public String create(LocalDateTime orderDate,String customerId, String status);

    // Read
    List<WinningRecords> getAllWinningRecordsByCustomerId(String customerId);
    public List<?> getAll();
    public Orders getOneByOrdernumber(String ordernumber);


    // Update
    public void update(Orders orders);
	public void updateOrderInfo(RecipientInformation recipient);
    // Delete
	public void delete(String orderId); 
	
	//自動下架用
	public void finalizeHighestBidAsOrder(PaintingDTO painting, LocalDateTime removeDate);

}
