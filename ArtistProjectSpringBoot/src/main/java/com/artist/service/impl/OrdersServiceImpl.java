package com.artist.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artist.dto.response.PaintingDTO;
import com.artist.entity.Bidrecord;
import com.artist.entity.Customers;
import com.artist.entity.Orders;
import com.artist.repository.BidrecordRepository;
import com.artist.repository.CustomersRepository;
import com.artist.repository.OrdersRepository;
import com.artist.service.OrdersService;
import com.artist.utils.IdGenerator;

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
		
		order.setOrderNumber(orderNumber);
		order.setOrderDate(orderDate);
		order.setStatus(status);
		order.setAttName("");
		order.setAttPhone("");
		order.setDeliveryAdress("");
		order.setDeliveryInstrictions("");
		
	    Customers customer = cr.findByCustomerId(customerId)
	            .orElseThrow(() -> new RuntimeException("Customer not found"));
	    order.setCustomer(customer);

	    System.out.println(order);
	    or.save(order); // 保存订单
		System.out.println(order);
		or.save(order);
		return order.getOrderNumber();
	}

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

			// 未實現
			// 用order表和orderdetail表查
//			esi.sendBidSuccessEmail(painting.getBidderEmail(), painting.getPaintingId(), painting.getFinalBidAmount());

		}

	}

}
