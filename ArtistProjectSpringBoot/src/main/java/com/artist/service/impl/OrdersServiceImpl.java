package com.artist.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artist.dto.request.RecipientInformation;
import com.artist.dto.response.OrdersDTO;
import com.artist.dto.response.PaintingDTO;
import com.artist.dto.response.WinningRecords;
import com.artist.entity.Bidrecord;
import com.artist.entity.Orders;
import com.artist.repository.BidrecordRepository;
import com.artist.repository.CustomersRepository;
import com.artist.repository.OrderDetailsRepository;
import com.artist.repository.OrdersRepository;
import com.artist.service.OrdersService;
import com.artist.utils.IdGenerator;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersRepository or;
	@Autowired
	private OrderDetailsRepository odr;
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
	public String create(LocalDateTime orderDate, String customerId, Integer serviceFee, Integer deposit, Integer totalAmount) {
	    Orders order = new Orders();
	    String orderNumber = idGenerator.orderId(); // 使用 ID 生成器生成訂單編號
	    order.setOrderNumber(orderNumber);
	    order.setOrderDate(orderDate);
	    order.setCustomerId(customerId);
	    
	    // 根據傳入的參數設置這些欄位
	    order.setServiceFee(serviceFee); // 設置服務費
	    order.setDesposit(deposit); // 設置訂金
	    order.setTotalAmount(totalAmount); // 設置總金額

	    or.save(order); // 保存訂單

	    return order.getOrderNumber(); // 返回生成的訂單號
	}
	
	@Override
	public void update(OrdersDTO ordersDTO) {
		String orderNum  = ordersDTO.getOrderNumber();
		
		Optional<Orders> optionalOrder = or.findByOrderNumber(orderNum);
		if (optionalOrder.isPresent()) { 
			Orders o = optionalOrder.get(); 
			o.setServiceFee(ordersDTO.getServiceFee());
			o.setDesposit(ordersDTO.getDesposit());
			o.setTotalAmount(ordersDTO.getTotalAmount());
			o.setDeliveryNumber(ordersDTO.getDeliveryNumber());
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
			//如果沒有出價紀錄
			System.out.println(painting.getPaintingId() + "沒有出價紀錄");
			return; //
		} else {
			//如果有出過價
			
			// 使用 Lambda 表達式更新每個 Bidrecord 的 status 並保存
			binddinglist.forEach(bid -> {
			    bid.setStatus("Auction closed");
			    brr.save(bid); // 儲存更新後的 Bidrecord
			});
			
			//取得最高出價記錄
			Bidrecord bidrecord = binddinglist.get(0);
			String customerId = bidrecord.getBidderId(); //得到customerId
			Double bidAmount = bidrecord.getBidAmount();
			String orderNumber = create(removeDate, customerId, 0, 0, bidAmount.intValue()); //這邊拿到orderNumber

			String paintingId = bidrecord.getPaintingId();
//			Double bidAmount = bidrecord.getBidAmount();

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
		odr.deleteById(orderId);
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
	public OrdersDTO getOneByOrdernumber(String ordernumber) {
		Optional<Orders> optionalOrder = or.findByOrderNumber(ordernumber);
		if (optionalOrder.isPresent()) { //OrdersDTO ordersDTO
			Orders o = optionalOrder.get();
			OrdersDTO ordersDTO = new OrdersDTO();
			ordersDTO.setOrderNumber(o.getOrderNumber());
			ordersDTO.setOrderDate(o.getOrderDate());
			ordersDTO.setCustomerId(o.getCustomerId());

			return ordersDTO;
		} else {
			System.out.println("not find");
			return null;
		}
	}


	
	@Override
	public void updateOrderInfo(RecipientInformation recipient) {
		
	}

}


