package com.artist.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.artist.dto.request.DeliveryOrderRequestDTO;
import com.artist.dto.response.DeliveryOrderResponseDTO;
import com.artist.dto.response.OrdersDTO;
import com.artist.entity.Deliveryorders;
import com.artist.entity.Orders;
import com.artist.repository.DeliveryOrderRepository;
import com.artist.repository.OrdersRepository;
import com.artist.service.DeliveryOrderService;
import com.artist.utils.IdGenerator;

@Service
public class DeliveryOrderServiceImpl implements DeliveryOrderService{
	
		@Autowired
	    private DeliveryOrderRepository deliveryOrderRepository;  // 注入 DeliveryOrderRepository

	    @Autowired
	    private IdGenerator idGenerator;  // 注入 Id 生成器
	    
	    @Autowired
	    private OrdersRepository ordersRepository; // 注入 OrdersRepository
	
	    @Override
		public String createDeliveryOrder(DeliveryOrderRequestDTO deliveryOrderRequestDTO) {
		
	    	// 使用 IdGenerator 生成唯一的 Delivery ID
	        String deliveryId = idGenerator.deliveryOrderId();
	        
	        try {
	            // 創建 Deliveryorders 實體
	            Deliveryorders deliveryOrder = new Deliveryorders();
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
	            deliveryOrderRepository.save(deliveryOrder);

	         // 更新每個訂單的 delivery_number
	            updateOrdersWithDeliveryNumber(deliveryOrderRequestDTO.getOrderList(), deliveryId);

	            // 返回成功訊息
	            return "出貨單已成立 : " + deliveryId;

	        } catch (Exception e) {
	            // 返回失敗訊息
	            return "出貨單成立失敗: " +deliveryId+ e.getLocalizedMessage();
	        }
	    }
	    
	    /**
	     * 根據前端傳回的 order_number，更新每個訂單的 delivery_number
	     */
	    private void updateOrdersWithDeliveryNumber(List<OrdersDTO> orderList, String deliveryId) {
	        for (OrdersDTO orderDTO : orderList) {
	            // 根據 order_number 查詢訂單
	            Optional<Orders> optionalOrder = ordersRepository.findByOrderNumber(orderDTO.getOrderNumber());
	            if (optionalOrder.isPresent()) {
	                Orders order = optionalOrder.get();
	                // 更新訂單的 delivery_number
	                order.setDeliveryNumber(deliveryId);
	                // 保存更新後的訂單
	                ordersRepository.save(order);
	            } else {
	                throw new RuntimeException("訂單號 " + orderDTO.getOrderNumber() + " 找不到");
	            }
	        }}
	    

	@Override
	public Optional<Deliveryorders> findByOrderNumber(String orderNumber) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Deliveryorders> findByDeliveryNumber(String deliveryNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deliveryorders> findByCreateDateBetween(LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsByOrderNumber(String orderNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Deliveryorders> findLatestDeliveryOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deliveryorders> findByDeliveryStaff(Integer staffId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deliveryorders> findByPackageStaff(Integer staffId) {
		// TODO Auto-generated method stub
		return null;
	}



	

	

}
