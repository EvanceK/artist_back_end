package com.artist.service;

import com.artist.dto.response.DeliveryOrdersDTO;

public interface DeliveryOrdersService {

	// create
	public void create(DeliveryOrdersDTO deliveryOrdersfDTO);
	
	// update
	public void update(DeliveryOrdersDTO deliveryOrdersfDTO);
	
}
