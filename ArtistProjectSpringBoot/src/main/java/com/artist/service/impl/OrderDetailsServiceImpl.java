package com.artist.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artist.dto.response.PaintingDTO;
import com.artist.entity.Bidrecord;
import com.artist.entity.OrderDetails;
import com.artist.repository.BidrecordRepository;
import com.artist.repository.OrderDetailsRepository;
import com.artist.service.OrderDetailsService;
import com.artist.utils.IdGenerator;
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{


	@Autowired
	BidrecordRepository brr;
	@Autowired
	EmailServiceImpl esi; 
	@Autowired
	OrderDetailsRepository odr;
	@Autowired
	OrdersServiceImpl  osi;
	
	
	@Autowired
	private IdGenerator idGenerator; // 注入 IdGenerator
	
	@Override
	public void create(String paintingId,Double bidAmount) {
		OrderDetails orderDetail = new OrderDetails();
		orderDetail.setOrderNumber(idGenerator.orderId());
		orderDetail.setPaintingId(paintingId);
		orderDetail.setPrice(bidAmount);
		odr.save(orderDetail);
	}
	
	public OrderDetails getByPaintingId(String paintingId){
		Optional<OrderDetails> byPaintingId = odr.findByPaintingId(paintingId);
		if (byPaintingId.isPresent()) {
			OrderDetails orderDetails = byPaintingId.get();			
			return orderDetails;
		}
		return null;
		}
	
	public void finalizeHighestBidAsOrder(PaintingDTO painting, LocalDateTime removeDate){
		//有查詢到有出過價錢，取最高的出價紀錄然後新增至orderdetail表。
		List<Bidrecord> binddinglist = brr.findByPaintingIdOrderByBidAmountDesc(painting.getPaintingId());
		Bidrecord bidrecord = binddinglist.get(0);
		String paintingId = bidrecord.getPaintingId();
		Double bidAmount = bidrecord.getBidAmount();
		create(paintingId,bidAmount);
		OrderDetails order = getByPaintingId(paintingId);
		order.getOrderNumber();//拿到ordernumber //用來新增order表
		String bidderId = bidrecord.getBidderId();
		osi.create(order.getOrderNumber(), removeDate, bidderId, "Pending Final Payment");
		
		
		//未實現
		//用order表和orderdetail表查 
//	    esi.sendBidSuccessEmail(painting.getBidderEmail(), painting.getPaintingId(), painting.getFinalBidAmount());
	}
	
}
