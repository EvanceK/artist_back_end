package com.artist.service;

import java.util.List;

import com.artist.dto.request.DeliveryOrderRequestDTO;
import com.artist.dto.response.DeliveryOrderResponseDTO;
import com.artist.dto.response.DeliveryOrdersDTO;

public interface DeliveryOrdersService {

	//前台創建訂單編號
	String createDeliveryOrder(DeliveryOrderRequestDTO deliveryOrderRequestDTO);
	
	
	
	//Select all
	List<DeliveryOrderResponseDTO> getAllWithOrders();

	DeliveryOrderResponseDTO getByOrderNumber(String deliveryNumber);
	List<DeliveryOrderResponseDTO> getByStatusWithOrdersAndDetails(String status);
	String getPackageStaffName(String staffId);
	String getDeliveryStaffName(String staffId);

	//後台update用
	void update(DeliveryOrdersDTO DOrdersfDTO);
	
}
