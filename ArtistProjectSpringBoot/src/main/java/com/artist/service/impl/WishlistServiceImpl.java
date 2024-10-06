package com.artist.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artist.dto.PaintingDTO;
import com.artist.dto.WishlistDTO;
import com.artist.entity.Customers;
import com.artist.entity.Wishlist;
import com.artist.repository.WishlistRepository;
import com.artist.service.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepository wr;
	@Autowired
	private PaintingsServiceImpl psi;
	@Autowired
	private CustomersServiceImpl csi;

	@Override
	public List<WishlistDTO> findAllWishlistWithPaintings(String customerId) {
		List<WishlistDTO> wishlistDTOLsit = new ArrayList<>();
		List<Wishlist> allwish = wr.findAllByCustomerId(customerId);
		for (Wishlist w : allwish) {
			String paintingId = w.getPaintingId();			
			PaintingDTO paintings = psi.getByPaintingsId(paintingId);
			WishlistDTO wishlistDTO = new WishlistDTO();
			wishlistDTO.setPaintingName(paintings.getPaintingName());
			wishlistDTO.setArtisName(paintings.getArtisName());
			wishlistDTO.setPrice(paintings.getPrice());
			wishlistDTO.setSmallUrl(paintings.getSmallUrl());
			wishlistDTOLsit.add(wishlistDTO);
		}
		return wishlistDTOLsit;
	}

	@Override
	@Transactional
	public void deleteFromWishlist(String customerId,String paintingId) {
		wr.deleteByCustomerIdAndPaintingId(customerId, paintingId);
	}

	@Override
	public void addToWishlist(String customerId, String paintingId) {
		Customers customer = csi.getByCustomerId(customerId);
				
		Wishlist wishlist = new Wishlist(customerId, paintingId);
		wishlist.setCustomer(customer);
		System.out.println(wishlist);
		wr.save(wishlist);

	}

}
