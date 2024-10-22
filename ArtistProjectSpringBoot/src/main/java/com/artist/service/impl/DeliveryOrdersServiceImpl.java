package com.artist.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.artist.dto.response.DeliveryOrdersDTO;
import com.artist.entity.DeliveryOrders;
import com.artist.repository.DeliveryOrdersRepository;
import com.artist.service.DeliveryOrdersService;
import com.artist.utils.IdGenerator;

public class DeliveryOrdersServiceImpl implements DeliveryOrdersService{

	@Autowired
	private DeliveryOrdersRepository dor;
	@Autowired
	private IdGenerator idGenerator; // 注入 IdGenerator

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
		// TODO Auto-generated method stub
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
		}else {
			System.out.println("Artist is not find");
		}		
	}

}
