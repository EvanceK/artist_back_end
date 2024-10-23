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
import com.artist.dto.response.PaintingDTO;
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
	public DeliveryOrdersDTO selectByOrderNumber(String orderNumber)
	{
		Optional<DeliveryOrders> optionalDeliveryOrders = dor.findById(orderNumber);
		if (optionalDeliveryOrders.isPresent()) {
			DeliveryOrders do1 = optionalDeliveryOrders.get();
			DeliveryOrdersDTO  dto = new DeliveryOrdersDTO(); 
			dto.setAttName(do1.getAttName());
			dto.setAttPhone(do1.getAttPhone());
			dto.setCreateDate(do1.getCreateDate());
			dto.setDeliveryAddress(do1.getDeliveryAddress());
			dto.setDeliveryFee(do1.getDeliveryFee());
			dto.setDeliveryInstrictions(do1.getDeliveryInstrictions());
			dto.setDeliveryNumber(do1.getDeliveryNumber());
			dto.setDeliveryStaff(do1.getDeliveryStaff());
			dto.setOrderNumber(do1.getAttName());
			dto.setPackageStaff(do1.getPackageStaff());
			dto.setStatus(do1.getStatus());
			dto.setTotalAmount(do1.getTotalAmount());
			return dto;
		} else {
			System.out.println("not find");
			return null;
		}
	}

	@Override
	public Optional<DeliveryOrders> findByOrderNumber(String orderNumber) {
		return Optional.empty();
	}

	@Override
	public List<DeliveryOrders> findByDeliveryNumber(String deliveryNumber) {
		return null;
	}

	@Override
	public List<DeliveryOrders> findByCreateDateBetween(LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsByOrderNumber(String orderNumber) {
		return false;
	}

	@Override
	public List<DeliveryOrders> findLatestDeliveryOrders() {
		return null;
	}

	@Override
	public List<DeliveryOrders> findByDeliveryStaff(Integer staffId) {
		return null;
	}

	@Override
	public List<DeliveryOrders> findByPackageStaff(Integer staffId) {
		return null;
	}

	@Override
	public void create(DeliveryOrdersDTO deliveryOrdersDTO) {
		DeliveryOrders dos = new DeliveryOrders();
		String deliveryNumber = idGenerator.DeliveryOrdersId();
		dos.setDeliveryNumber(deliveryNumber);
		dos.setAttName(deliveryOrdersDTO.getAttName());
		dos.setAttPhone(deliveryOrdersDTO.getAttPhone());
		dos.setCreateDate(deliveryOrdersDTO.getCreateDate());
		dos.setDeliveryAddress(deliveryOrdersDTO.getDeliveryAddress());
		dos.setDeliveryFee(deliveryOrdersDTO.getDeliveryFee());
		dos.setDeliveryInstrictions(deliveryOrdersDTO.getDeliveryInstrictions());
		dos.setDeliveryStaff(deliveryOrdersDTO.getDeliveryStaff());
		dos.setPackageStaff(deliveryOrdersDTO.getPackageStaff());
		dos.setStatus(deliveryOrdersDTO.getStatus());
		dos.setTotalAmount(deliveryOrdersDTO.getTotalAmount());
		dor.save(dos);
	}

	@Override
	public void update(DeliveryOrdersDTO DOrdersfDTO) {
		Optional<DeliveryOrders> Dorders = dor.findById(DOrdersfDTO.getDeliveryNumber());
		if (Dorders.isPresent()) {
			DeliveryOrders dos = Dorders.get();
			dos.setAttName(DOrdersfDTO.getAttName());
			dos.setAttPhone(DOrdersfDTO.getAttPhone());
			dos.setCreateDate(DOrdersfDTO.getCreateDate());
			dos.setDeliveryAddress(DOrdersfDTO.getDeliveryAddress());
			dos.setDeliveryFee(DOrdersfDTO.getDeliveryFee());
			dos.setDeliveryInstrictions(DOrdersfDTO.getDeliveryInstrictions());
			dos.setDeliveryStaff(DOrdersfDTO.getDeliveryStaff());
			dos.setPackageStaff(DOrdersfDTO.getPackageStaff());
			dos.setStatus(DOrdersfDTO.getStatus());
			dos.setTotalAmount(DOrdersfDTO.getTotalAmount());
			dor.save(dos);
		} else {
			System.out.println("Artist is not find");
		}
	}

	@Override
	public List<DeliveryOrderResponseDTO> findAllWithOrders() {

		ArrayList<DeliveryOrderResponseDTO> arrayList = new ArrayList<>();

		List<DeliveryOrders> allWithOrders = dor.findAllWithOrders();
		for (DeliveryOrders d : allWithOrders) {
			DeliveryOrderResponseDTO doDTO = new DeliveryOrderResponseDTO();
			doDTO.setDeliveryNumber(d.getDeliveryNumber());
			doDTO.setCreateDate(d.getCreateDate());
			doDTO.setStatus(d.getStatus());
			doDTO.setAttName(d.getAttName());
			doDTO.setAttPhone(d.getAttPhone());
			doDTO.setDeliveryAddress(d.getDeliveryAddress());
			doDTO.setDeliveryInstrictions(d.getDeliveryInstrictions());
			doDTO.setDeliveryFee(d.getDeliveryFee());
			doDTO.setTotalAmount(d.getTotalAmount());
			doDTO.setPackageStaff(d.getPackageStaff());
			doDTO.setDeliveryStaff(	d.getDeliveryStaff());
			
			List<Orders> orders = d.getOrders();
			List<OrdersDTO> ordersDTOList = orders.stream()
				    .map(o -> new OrdersDTO(
				        o.getOrderNumber(),
				        o.getOrderDate(),
				        o.getCustomerId(),
				        o.getServiceFee(),
				        o.getDesposit(),
				        o.getTotalAmount(),
				        o.getDeliveryNumber()
				    ))
				    .collect(Collectors.toList());
			
			doDTO.setOrderList(ordersDTOList);
			arrayList.add(doDTO);
		}
		return arrayList;
	}

}
