package com.artist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artist.dto.request.DeliveryOrderRequestDTO;
import com.artist.dto.response.DeliveryOrderResponseDTO;
import com.artist.entity.Deliveryorders;
import com.artist.repository.OrdersRepository;
import com.artist.service.DeliveryOrderService;

@RestController
@RequestMapping("/DeliveryOrderController")
public class DeliveryOrderController {
	
	@Autowired
    private DeliveryOrderService dos;
	
	
	

	
	@PostMapping(value = "/createDeliveryOrder", consumes = "application/json")
	public String createDeliveryOrder(@RequestBody DeliveryOrderRequestDTO deliveryOrderRequestDTO) {
        // 呼叫 Service 層的 createDeliveryOrder 方法
        return dos.createDeliveryOrder(deliveryOrderRequestDTO);
       }
//	
//	@GetMapping("/deliveryOrders/{deliveryId}")
//	public DeliveryOrderResponseDTO getDeliveryOrderDetails(@PathVariable String deliveryId) {
//	    Deliveryorders deliveryOrder = deliveryOrderRepository.findById(deliveryId)
//	        .orElseThrow(() -> new RuntimeException("出貨單號 " + deliveryId + " 找不到"));
//
//	    // 將相關訂單數據傳遞給前端
//	    List<OrdersDTO> ordersDTOList = deliveryOrder.getOrderList().stream()
//	        .map(order -> new OrdersDTO(order.getOrderNumber(), order.getTotalAmount(), ...))
//	        .collect(Collectors.toList());
//
//	    DeliveryOrderResponseDTO responseDTO = new DeliveryOrderResponseDTO();
//	    responseDTO.setDeliveryNumber(deliveryOrder.getDeliveryNumber());
//	    responseDTO.setOrderList(ordersDTOList);
//	    
//	    return responseDTO;
//	}
}
