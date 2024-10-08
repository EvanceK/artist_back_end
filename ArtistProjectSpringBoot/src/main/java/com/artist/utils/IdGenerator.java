package com.artist.utils;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artist.entity.Artist;
import com.artist.entity.Customers;
import com.artist.entity.Paintings;
import com.artist.repository.ArtistRepository;
import com.artist.repository.CustomersRepository;
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
	public String paintingId(Connection conn) {
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
//	static public String inventoriesId() {
//		String prefix = "IN";
//		InventoriesDaoImpl idi = new InventoriesDaoImpl();
//		List<Inventories> inventoriesList = idi.selectAll();
//		if (inventoriesList.size() > 0) {
//			Inventories lastesInventories = inventoriesList.get(inventoriesList.size() - 1);
//			String lastestId = lastesInventories.getInventoryNumber();
//			return IDGenerator(prefix, lastestId);
//		} else {
//			return IDGenerator(prefix, "0000");
//		}
//	}

//
//	static public String orderId() {
//		String prefix = "OR";
//		OrdersDaoImpl odi = new OrdersDaoImpl();
//		List<Orders> ordersList = odi.selectAll();
//		if (ordersList.size() > 0) {
//			Orders lastesOrders = ordersList.get(ordersList.size() - 1);
//			String lastestId = lastesOrders.getOrderNumber();
//			return IDGenerator(prefix, lastestId);
//		} else {
//			return IDGenerator(prefix, "0000");
//		}
//	}
	
	
}
