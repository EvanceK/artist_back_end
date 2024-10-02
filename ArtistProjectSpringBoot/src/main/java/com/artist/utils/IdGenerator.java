package com.artist.utils;

import java.sql.Connection;
import java.util.List;

import com.artist.dao.impl.ArtistDaoImpl;
import com.artist.dao.impl.CartsDaoImpl;
import com.artist.dao.impl.CustomersDaoImpl;
import com.artist.dao.impl.InventoriesDaoImpl;
import com.artist.dao.impl.OrdersDaoImpl;
import com.artist.dao.impl.PaintingsDaoImpl;
import com.artist.entity.Artist;
import com.artist.entity.Carts;
import com.artist.entity.Customers;
import com.artist.entity.Inventories;
import com.artist.entity.Orders;
import com.artist.entity.Paintings;

public class IdGenerator {
	static Integer numCodelenght = 4;

	static public String artistId() {
		String prefix = "AR";
		ArtistDaoImpl adi = new ArtistDaoImpl();
		List<Artist> artList = adi.selectAll();
		if (artList.size() > 0) {
			Artist lastesArtist = artList.get(artList.size() - 1);
			String lastestId = lastesArtist.getArtisId();
			return IDGenerator(prefix, lastestId);
		} else {
			return IDGenerator(prefix, "0000");
		}
	}

//	static public String cartsId() {
//		String prefix = "CA";
//		CartsDaoImpl ctdi = new CartsDaoImpl();
//		List<Carts> cartList = ctdi.selectAll();
//		if (cartList.size() > 0) {
//			Carts lastesCarts = cartList.get(cartList.size() - 1);
//			String lastestId = lastesCarts();
//			return IDGenerator(prefix, lastestId);
//		} else {
//			return IDGenerator(prefix, "0000");
//		}
//	}

	static public String customersId() {
		String prefix = "CU";
		CustomersDaoImpl cdi = new CustomersDaoImpl();
		List<Customers> customersList = cdi.selectAll();
		if (customersList.size() > 0) {
			Customers lastesCustomers = customersList.get(customersList.size() - 1);
			String lastestId = lastesCustomers.getCustomerId();
			return IDGenerator(prefix, lastestId);
		} else {
			return IDGenerator(prefix, "0000");
		}

	}

	static public String inventoriesId() {
		String prefix = "IN";
		InventoriesDaoImpl idi = new InventoriesDaoImpl();
		List<Inventories> inventoriesList = idi.selectAll();
		if (inventoriesList.size() > 0) {
			Inventories lastesInventories = inventoriesList.get(inventoriesList.size() - 1);
			String lastestId = lastesInventories.getInventoryNumber();
			return IDGenerator(prefix, lastestId);
		} else {
			return IDGenerator(prefix, "0000");
		}
	}


	static public String orderId() {
		String prefix = "OR";
		OrdersDaoImpl odi = new OrdersDaoImpl();
		List<Orders> ordersList = odi.selectAll();
		if (ordersList.size() > 0) {
			Orders lastesOrders = ordersList.get(ordersList.size() - 1);
			String lastestId = lastesOrders.getOrderNumber();
			return IDGenerator(prefix, lastestId);
		} else {
			return IDGenerator(prefix, "0000");
		}
	}
	
	static public String paintingId(Connection conn) {
		String prefix = "PT";
		PaintingsDaoImpl pdi = new PaintingsDaoImpl();
		List<Paintings> paintingsList = pdi.selectAll();
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
}
