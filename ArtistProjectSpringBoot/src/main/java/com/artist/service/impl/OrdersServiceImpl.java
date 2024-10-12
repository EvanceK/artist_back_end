package com.artist.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artist.entity.Orders;
import com.artist.repository.OrdersRepository;
import com.artist.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService{
	@Autowired
	private OrdersRepository or;

	@Override
	public void create(String orderNumber,LocalDateTime orderDate,String customerId, String status) {
		Orders order = new Orders();
		order.setOrderNumber(orderNumber);
		order.setOrderDate(orderDate);
		order.setCustomerId(customerId);
		order.setStatus(status);
		order.setAttName("");
		order.setAttPhone("");
		order.setDeliveryAdress("");
		order.setDeliveryInstrictions("");
		or.save(order);
	}
}
