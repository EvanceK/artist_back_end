package com.artist.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.artist.dto.request.DeliveryOrderRequestDTO;
import com.artist.dto.response.DeliveryOrderResponseDTO;
import com.artist.dto.response.DeliveryOrdersDTO;
import com.artist.dto.response.MyOrderResponse;

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
	
	
	List<MyOrderResponse> getByDeliveryNumberAndCustomer(@Param("customerId") String customerId);

	
}
