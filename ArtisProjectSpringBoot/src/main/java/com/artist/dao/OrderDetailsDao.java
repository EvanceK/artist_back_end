package com.artist.dao;

import java.util.List;

import com.artist.bean.OrderDetails;


public interface OrderDetailsDao {
	
	// Create
			void create(OrderDetails odl);

			// Read
			List<OrderDetails> selectAll();

			// Update
			void update(OrderDetails odl);

			// Delete
			void delete(String orderNumber);

			void update(OrderDetails preodl, OrderDetails newodl);

			void create(String orderNumber, String paintingId, Double price);

}
