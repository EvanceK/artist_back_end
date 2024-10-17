package com.artist.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artist.dto.request.RecipientInformation;
import com.artist.dto.response.PaintingDTO;
import com.artist.dto.response.WinningRecords;
import com.artist.entity.Bidrecord;
import com.artist.entity.Customers;
import com.artist.entity.Orders;
import com.artist.repository.BidrecordRepository;
import com.artist.repository.CustomersRepository;
import com.artist.repository.OrdersRepository;
import com.artist.service.OrdersService;
import com.artist.utils.IdGenerator;

import jakarta.mail.MessagingException;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersRepository or;

	@Autowired
	BidrecordRepository brr;
	@Autowired
	EmailServiceImpl esi;
	@Autowired
	OrderDetailsServiceImpl odsi;
    @Autowired
	CustomersRepository cr;
	@Autowired
	private IdGenerator idGenerator; // 注入 IdGenerator

	@Override
	public String create(LocalDateTime orderDate, String customerId, String status) {
		Orders order = new Orders();
		String orderNumber = idGenerator.orderId();
		Optional<Customers> byCustomerId = cr.findByCustomerId(customerId);
		order.setOrderNumber(orderNumber);
		order.setOrderDate(orderDate);
		order.setStatus(status);
		order.setCustomerId(customerId);
		order.setAttName("");
		order.setAttPhone("");
		order.setDeliveryAdress("");
		order.setDeliveryInstrictions("");
		
		or.save(order);
		return order.getOrderNumber();
	}
	
	@Override
	public void update(Orders orders) {
		String orderNum  = orders.getOrderNumber();
		String status = orders.getStatus();
		
		Optional<Orders> optionalOrder = or.findByOrderNumber(orderNum);
		if (optionalOrder.isPresent()) { 
			Orders o = optionalOrder.get(); 
			o.setStatus(status);
			or.save(o);
		} else {
			System.out.println("not find");
		}		
	}
	
	@Transactional
	public void finalizeHighestBidAsOrder(PaintingDTO painting, LocalDateTime removeDate) {
		// 有查詢到有出過價錢，取最高的出價紀錄然後新增至order表。
		List<Bidrecord> binddinglist = brr.findByPaintingIdOrderByBidAmountDesc(painting.getPaintingId());

		if (binddinglist.isEmpty()) {

			System.out.println(painting.getPaintingId() + "沒有出價紀錄");
			return; //
		} else {
			//如果有出過價
			
			// 使用 Lambda 表達式更新每個 Bidrecord 的 status 並保存
			binddinglist.forEach(bid -> {
			    bid.setStatus("Auction closed");
			    brr.save(bid); // 儲存更新後的 Bidrecord
			});
		
			Bidrecord bidrecord = binddinglist.get(0);
			String customerId = bidrecord.getBidderId(); //得到customerId
			
			String orderNumber = create(removeDate, customerId, "Pending Final Payment"); //這邊拿到orderNumber

			String paintingId = bidrecord.getPaintingId();
			Double bidAmount = bidrecord.getBidAmount();

			odsi.create(orderNumber, paintingId, bidAmount);
//			try {
//				esi.sendAuctionWinningEmail(painting.getPaintingId());
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
//			
		}

	}
	@Override
	public List<WinningRecords> getAllWinningRecordsByCustomerId(String customerId) {
		List<Orders> orderList = or.findByCustomerId(customerId);
		List<WinningRecords> WinningRecordslist = new ArrayList<>();

		if (orderList.isEmpty()) {
			return null;
		}else {
			for(Orders o:orderList) {
			
				WinningRecords winningRecords = new WinningRecords();
				winningRecords.setOrderNumber(o.getOrderNumber());
				winningRecords.setPaintingId(o.getOrderDetail().getPaintingId());
				winningRecords.setPaintingName(o.getOrderDetail().getPainting().getPaintingName());
				winningRecords.setPrice(o.getOrderDetail().getPrice()*0.9);//收剩下的9成
				winningRecords.setSmallUrl(o.getOrderDetail().getPainting().getSmallUrl());
				winningRecords.setArtistId(o.getOrderDetail().getPainting().getArtist().getArtistId());
				winningRecords.setArtisName(o.getOrderDetail().getPainting().getArtist().getArtistName());
				WinningRecordslist.add(winningRecords);
			}
		}
		return WinningRecordslist;
	
	}
	
	@Override
	public void delete(String orderId) {
		or.deleteById(orderId);
	}

	@Override
	public List<Orders> getAll() {
		List<Orders> orderList = or.findAll();
		if (orderList.isEmpty()) {
			return null;
		}else {
			return orderList;
		}
	}

	@Override
	public Orders getOneByOrdernumber(String ordernumber) {
		Optional<Orders> optionalOrder = or.findByOrderNumber(ordernumber);
		if (optionalOrder.isPresent()) { 
			Orders o = optionalOrder.get(); 
			return o;
		} else {
			System.out.println("not find");
			return null;
		}
	}

	@Override
	public void updateOrderInfo(RecipientInformation recipient) {
		
	Optional<Orders> orderNumber = or.findByOrderNumber(recipient.getOrdernumber());
	if (orderNumber.isPresent()) {
		Orders order = orderNumber.get();
		order.setDeliveryAdress(recipient.getDeliveryAdress());
		order.setAttName(recipient.getAttName());
		order.setAttPhone(recipient.getAttPhone());
		order.setDeliveryInstrictions(recipient.getDeliveryInstrictions());
		or.save(order);
	}else {
		  throw new RuntimeException("資料填入異常");
	}
		
	}

}
