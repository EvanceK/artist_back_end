package dao;

import java.util.List;

import bean.OrderDetails;


public interface OrderDetailsDao {
	
	// Create
			void create(OrderDetails odl);

			// Read
			List<OrderDetails> selectAll();

			// Update
			void update(OrderDetails odl);

			// Delete
			void delete(String orderNumber);

}
