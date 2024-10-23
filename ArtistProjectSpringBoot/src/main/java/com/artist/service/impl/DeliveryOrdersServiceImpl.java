package com.artist.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artist.dto.request.DeliveryOrderRequestDTO;
import com.artist.dto.response.DeliveryOrderResponseDTO;
import com.artist.dto.response.DeliveryOrdersDTO;
import com.artist.dto.response.OrdersDTO;
import com.artist.entity.DeliveryOrders;
import com.artist.entity.Orders;
import com.artist.repository.DeliveryOrdersRepository;
import com.artist.repository.OrdersRepository;
import com.artist.service.DeliveryOrdersService;
import com.artist.utils.IdGenerator;

@Service
public class DeliveryOrdersServiceImpl implements DeliveryOrdersService {

	@Autowired
	private IdGenerator idGenerator; // 注入 IdGenerator
	@Autowired
	private DeliveryOrdersRepository dor; // 注入 DeliveryOrderRepository

	@Autowired
	private OrdersRepository or; // 注入 OrdersRepository

	@Override
	public String createDeliveryOrder(DeliveryOrderRequestDTO deliveryOrderRequestDTO) {

		// 使用 IdGenerator 生成唯一的 Delivery ID
		String deliveryId = idGenerator.deliveryOrderId();

		try {
			// 創建 Deliveryorders 實體
			DeliveryOrders deliveryOrder = new DeliveryOrders();
			deliveryOrder.setDeliveryNumber(deliveryId);
			deliveryOrder.setCreateDate(LocalDateTime.now());

			// 設定狀態為 "待處理"
			deliveryOrder.setStatus("待處理");

			// 設置寄送資訊
			deliveryOrder.setAttName(deliveryOrderRequestDTO.getAttName());
			deliveryOrder.setAttPhone(deliveryOrderRequestDTO.getAttPhone());
			deliveryOrder.setDeliveryAddress(deliveryOrderRequestDTO.getDeliveryAddress());
			deliveryOrder.setDeliveryInstrictions(deliveryOrderRequestDTO.getDeliveryInstrictions());
			deliveryOrder.setDeliveryFee(deliveryOrderRequestDTO.getDeliveryFee());
			deliveryOrder.setTotalAmount(deliveryOrderRequestDTO.getTotalAmount());

			// 保存出貨單，保證 deliveryId 已經存在
			dor.save(deliveryOrder);

			// 更新每個訂單的 delivery_number
			updateOrdersWithDeliveryNumber(deliveryOrderRequestDTO.getOrderList(), deliveryId);

			// 返回成功訊息
			return "出貨單已成立 : " + deliveryId;

		} catch (Exception e) {
			// 返回失敗訊息
			return "出貨單成立失敗: " + deliveryId + e.getLocalizedMessage();
		}
	}

	/**
	 * 根據前端傳回的 order_number，更新每個訂單的 delivery_number
	 */
	private void updateOrdersWithDeliveryNumber(List<OrdersDTO> orderList, String deliveryId) {
		for (OrdersDTO orderDTO : orderList) {
			// 根據 order_number 查詢訂單
			Optional<Orders> optionalOrder = or.findByOrderNumber(orderDTO.getOrderNumber());
			if (optionalOrder.isPresent()) {
				Orders order = optionalOrder.get();
				// 更新訂單的 delivery_number
				order.setDeliveryNumber(deliveryId);
				// 保存更新後的訂單
				or.save(order);
			} else {
				throw new RuntimeException("訂單號 " + orderDTO.getOrderNumber() + " 找不到");
			}
		}
	}
	


	@Override
	public void update(DeliveryOrdersDTO DOrdersfDTO) {
		Optional<DeliveryOrders> Dorders = dor.findById(DOrdersfDTO.getDeliveryNumber());
		if (Dorders.isPresent()) {
			DeliveryOrders dos = Dorders.get();
			dos.setAttName(DOrdersfDTO.getAttName());
			dos.setAttPhone(DOrdersfDTO.getAttPhone());
//			dos.setCreateDate(DOrdersfDTO.getCreateDate());
			dos.setDeliveryAddress(DOrdersfDTO.getDeliveryAddress());
//			dos.setDeliveryFee(DOrdersfDTO.getDeliveryFee());
			dos.setDeliveryInstrictions(DOrdersfDTO.getDeliveryInstrictions());
			dos.setDeliveryStaff(DOrdersfDTO.getDeliveryStaff());
			dos.setPackageStaff(DOrdersfDTO.getPackageStaff());
			dos.setStatus(DOrdersfDTO.getStatus());
//			dos.setTotalAmount(DOrdersfDTO.getTotalAmount());
			dor.save(dos);
		} else {
			System.out.println("Artist is not find");
		}
	}

	@Override
	public List<DeliveryOrderResponseDTO> getAllWithOrders() {
		 List<DeliveryOrders> allWithOrders = dor.findAllWithOrdersAndDetails();
		    List<DeliveryOrderResponseDTO> responseDTOList = new ArrayList<>();

		    for (DeliveryOrders deliveryOrder : allWithOrders) {
		        DeliveryOrderResponseDTO deliveryOrderDTO = new DeliveryOrderResponseDTO();
		        deliveryOrderDTO.setDeliveryNumber(deliveryOrder.getDeliveryNumber());
		        deliveryOrderDTO.setCreateDate(deliveryOrder.getCreateDate());
		        deliveryOrderDTO.setStatus(deliveryOrder.getStatus());
		        deliveryOrderDTO.setAttName(deliveryOrder.getAttName());
		        deliveryOrderDTO.setAttPhone(deliveryOrder.getAttPhone());
		        deliveryOrderDTO.setDeliveryAddress(deliveryOrder.getDeliveryAddress());
		        deliveryOrderDTO.setDeliveryInstrictions(deliveryOrder.getDeliveryInstrictions());
		        deliveryOrderDTO.setDeliveryFee(deliveryOrder.getDeliveryFee());
		        deliveryOrderDTO.setTotalAmount(deliveryOrder.getTotalAmount());
		        deliveryOrderDTO.setPackageStaff(deliveryOrder.getPackageStaff());
		        deliveryOrderDTO.setDeliveryStaff(deliveryOrder.getDeliveryStaff());

		        // 提取訂單列表
		        List<OrdersDTO> ordersDTOList = deliveryOrder.getOrders().stream()
		            .map(order -> {
		                String paintingId = order.getOrderDetail().getPaintingId();
		                
		                return new OrdersDTO(
		                    order.getOrderNumber(),
		                    order.getOrderDate(),
		                    order.getCustomerId(),
		                    order.getServiceFee(),
		                    order.getDesposit(),
		                    order.getTotalAmount(),
		                    order.getDeliveryNumber(),
		                    paintingId
		                );
		            })
		            .collect(Collectors.toList());

		        deliveryOrderDTO.setOrderList(ordersDTOList);
		        responseDTOList.add(deliveryOrderDTO);
		    }
		    return responseDTOList;
	}
	
	@Override
	public DeliveryOrderResponseDTO getByOrderNumber(String deliveryNumber)	{
		
			Optional<DeliveryOrders> optionalDeliveryOrders = dor.findByDeliveryNumberWithOrdersAndDetails(deliveryNumber);
			if (optionalDeliveryOrders.isPresent()) {
			DeliveryOrders deliveryOrder = optionalDeliveryOrders.get();
			DeliveryOrderResponseDTO deliveryOrderDTO = new DeliveryOrderResponseDTO();
	        deliveryOrderDTO.setDeliveryNumber(deliveryOrder.getDeliveryNumber());
	        deliveryOrderDTO.setCreateDate(deliveryOrder.getCreateDate());
	        deliveryOrderDTO.setStatus(deliveryOrder.getStatus());
	        deliveryOrderDTO.setAttName(deliveryOrder.getAttName());
	        deliveryOrderDTO.setAttPhone(deliveryOrder.getAttPhone());
	        deliveryOrderDTO.setDeliveryAddress(deliveryOrder.getDeliveryAddress());
	        deliveryOrderDTO.setDeliveryInstrictions(deliveryOrder.getDeliveryInstrictions());
	        deliveryOrderDTO.setDeliveryFee(deliveryOrder.getDeliveryFee());
	        deliveryOrderDTO.setTotalAmount(deliveryOrder.getTotalAmount());
	        deliveryOrderDTO.setPackageStaff(deliveryOrder.getPackageStaff());
	        deliveryOrderDTO.setDeliveryStaff(deliveryOrder.getDeliveryStaff());
	     // 提取訂單列表
	        List<OrdersDTO> ordersDTOList = deliveryOrder.getOrders().stream()
	            .map(order -> {
	                String paintingId = order.getOrderDetail().getPaintingId();
	                
	                return new OrdersDTO(
	                    order.getOrderNumber(),
	                    order.getOrderDate(),
	                    order.getCustomerId(),
	                    order.getServiceFee(),
	                    order.getDesposit(),
	                    order.getTotalAmount(),
	                    order.getDeliveryNumber(),
	                    paintingId
	                );
	                
	            })
	            .collect(Collectors.toList());
	   
	        deliveryOrderDTO.setOrderList(ordersDTOList);
	        return deliveryOrderDTO;
		}else {
		    return null;
		}
	}

	@Override
	public List<DeliveryOrderResponseDTO> getByStatusWithOrdersAndDetails(String status) {
		 List<DeliveryOrders> allDeliveryOrdersByStatus = dor.findByStatusWithOrdersAndDetails(status);
		    List<DeliveryOrderResponseDTO> responseDTOList = new ArrayList<>();

		    for (DeliveryOrders deliveryOrder : allDeliveryOrdersByStatus) {
		        DeliveryOrderResponseDTO deliveryOrderDTO = new DeliveryOrderResponseDTO();
		        deliveryOrderDTO.setDeliveryNumber(deliveryOrder.getDeliveryNumber());
		        deliveryOrderDTO.setCreateDate(deliveryOrder.getCreateDate());
		        deliveryOrderDTO.setStatus(deliveryOrder.getStatus());
		        deliveryOrderDTO.setAttName(deliveryOrder.getAttName());
		        deliveryOrderDTO.setAttPhone(deliveryOrder.getAttPhone());
		        deliveryOrderDTO.setDeliveryAddress(deliveryOrder.getDeliveryAddress());
		        deliveryOrderDTO.setDeliveryInstrictions(deliveryOrder.getDeliveryInstrictions());
		        deliveryOrderDTO.setDeliveryFee(deliveryOrder.getDeliveryFee());
		        deliveryOrderDTO.setTotalAmount(deliveryOrder.getTotalAmount());
		        deliveryOrderDTO.setPackageStaff(deliveryOrder.getPackageStaff());
		        deliveryOrderDTO.setDeliveryStaff(deliveryOrder.getDeliveryStaff());

		        // 提取訂單列表
		        List<OrdersDTO> ordersDTOList = deliveryOrder.getOrders().stream()
		            .map(order -> {
		                String paintingId = order.getOrderDetail().getPaintingId();
		                
		                return new OrdersDTO(
		                    order.getOrderNumber(),
		                    order.getOrderDate(),
		                    order.getCustomerId(),
		                    order.getServiceFee(),
		                    order.getDesposit(),
		                    order.getTotalAmount(),
		                    order.getDeliveryNumber(),
		                    paintingId
		                );
		            })
		            .collect(Collectors.toList());

		        deliveryOrderDTO.setOrderList(ordersDTOList);
		        responseDTOList.add(deliveryOrderDTO);
		    }
		    return responseDTOList;
	}
}
