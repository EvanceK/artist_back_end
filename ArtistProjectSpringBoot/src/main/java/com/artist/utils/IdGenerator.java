package com.artist.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artist.entity.Artist;
import com.artist.entity.Customers;
import com.artist.entity.Deliveryorders;
import com.artist.entity.OrderDetails;
import com.artist.entity.Paintings;
import com.artist.repository.ArtistRepository;
import com.artist.repository.CustomersRepository;
import com.artist.repository.DeliveryOrderRepository;
import com.artist.repository.OrderDetailsRepository;
import com.artist.repository.PaintingsRepository;
@Component
public class IdGenerator {
	static Integer numCodelenght = 4;
	@Autowired
	private CustomersRepository cr;	 
	@Autowired
	private PaintingsRepository ptr;	
	@Autowired
	private ArtistRepository arr;	 
	@Autowired
	private OrderDetailsRepository odr;
	@Autowired
	private DeliveryOrderRepository dor;


	
	public String artistId() {
		String prefix = "AR";
		List<Artist> artList = arr.findAll();
		if (artList.size() > 0) {
			Artist lastesArtist = artList.get(artList.size() - 1);
			String lastestId = lastesArtist.getArtistId();
			return IDGenerator(prefix, lastestId);
		} else {
			return IDGenerator(prefix, "0000");
		}
	}
	public String customersId() {
		String prefix = "CU";
		List<Customers> all = cr.findAll();
		if (all.size() > 0) {
			Customers lastesCustomers = all.get(all.size() - 1);
			String lastestId = lastesCustomers.getCustomerId();
			return IDGenerator(prefix, lastestId);
		} else {
			return IDGenerator(prefix, "0000");
		}

	}
	public String paintingId() {
		String prefix = "PT";
		List<Paintings> paintingsList = ptr.findAll();
		if (paintingsList.size() > 0) {
			Paintings lastesPaintings = paintingsList.get(paintingsList.size() - 1);
			String lastestId = lastesPaintings.getPaintingId();
			return IDGenerator(prefix, lastestId);
		} else {
			return IDGenerator(prefix, "0000");
		}
	}

	public static String IDGenerator(String prefix, String id) {
		Integer number = 0;
		String pre = prefix;
		try {
			String sub = id.substring(id.length() - numCodelenght, id.length());
			number = Integer.parseInt(sub) + 1;
		} catch (Exception e) {
			number = 1;
		}

		String newSub = "" + number + "";
		for (int i = 0; i < numCodelenght; i++) {
			if (newSub.length() < numCodelenght) {
				newSub = "0" + newSub;
			}
		}

		return pre + newSub;

	}
	
	public String orderId() {
		String prefix = "OR";
		List<OrderDetails> orderDetailsList = odr.findAll();
		
		
		if (orderDetailsList.size() > 0) {
			OrderDetails lastesOrders = orderDetailsList.get(orderDetailsList.size() - 1);
			String lastestId = lastesOrders.getOrderNumber();
			return IDGenerator(prefix, lastestId);
		} else {
			return IDGenerator(prefix, "0000");
		}
	}
	
	// 新增的 deliveryOrderId 生成方法（DO + yyyyMMdd + 流水號）
	public static String IDGeneratorDO(String prefix, String id) {
	    Integer number = 0;
	    String pre = prefix;

	    // 生成當前日期，格式為 yyyyMMdd
	    String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

	    try {
	        // 提取流水號的日期部分和編號部分
	        String lastDate = id.substring(2, 10); // 假設前綴為 "DO" 並且日期為 yyyyMMdd 共8位
	        String sub = id.substring(id.length() - 4); // 假設流水號為4位

	        // 如果前一筆的日期與當前日期不相同，則流水號從 1 開始
	        if (!lastDate.equals(currentDate)) {
	            number = 1;
	        } else {
	            number = Integer.parseInt(sub) + 1; // 流水號自增
	        }
	    } catch (Exception e) {
	        number = 1; // 如果出錯，則流水號從 1 開始
	    }

	    // 使用 %04d 保證流水號是 4 位，不足補零
	    String newSub = String.format("%04d", number);

	    // 最終返回格式為 "DO + yyyyMMdd + 流水號"
	    return pre + currentDate + newSub;
	}

	// 新增生成 deliveryOrderId 的方法
	public String deliveryOrderId() {
	    String prefix = "DO";
	    List<Deliveryorders> deliveryOrderList = dor.findAll();
	    
	    if (!deliveryOrderList.isEmpty()) {
	        Deliveryorders lastestDeliveryOrder = deliveryOrderList.get(deliveryOrderList.size() - 1);
	        String lastestId = lastestDeliveryOrder.getDeliveryNumber();

	        // 檢查日期是否與當前日期一致
	        return IDGeneratorDO(prefix, lastestId);
	    } else {
	        // 如果沒有訂單，從 "DO" + 當天的 "0001" 開始
	        return IDGeneratorDO(prefix, "DO" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "0000");
	    }
	}
	
}
