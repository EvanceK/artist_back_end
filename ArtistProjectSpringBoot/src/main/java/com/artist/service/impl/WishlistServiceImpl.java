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
		List<WishlistDTO> wishlistDTOList = new ArrayList<>();
		List<Wishlist> allwish = wr.findAllById_CustomerId(customerId);
		for (Wishlist w : allwish) {
			String paintingId = w.getId().getPaintingId();			
			PaintingDTO paintings = psi.getByPaintingsId(paintingId);
			WishlistDTO wishlistDTO = new WishlistDTO();
			wishlistDTO.setPaintingId(paintingId);
			wishlistDTO.setPaintingName(paintings.getPaintingName());
			wishlistDTO.setArtisName(paintings.getArtisName());
			wishlistDTO.setPrice(paintings.getPrice());
			wishlistDTO.setSmallUrl(paintings.getSmallUrl());
			wishlistDTOList.add(wishlistDTO);
		}
		return wishlistDTOList;
	}

	@Override
	@Transactional
	public void deleteFromWishlist(String customerId,String paintingId) {
		wr.deleteById_CustomerIdAndId_PaintingId(customerId, paintingId);
	}

	@Override
	public void addToWishlist(String customerId, String paintingId) {
		Customers customer = csi.getByCustomerId(customerId);
		if(psi.existsBypaintingId(paintingId)) {
			Wishlist wishlist = new Wishlist(customerId, paintingId);
			wishlist.setCustomer(customer);
			System.out.println(wishlist);
			wr.save(wishlist);
		}else {
			  System.out.println("畫作id不存在");
		      throw new IllegalArgumentException("畫作ID不存在: " + paintingId);
		}


	}

	@Override
	public boolean existsBycustomerIdAndpaintingId(String customerId, String paintingId) {
		
		return wr.existsById_CustomerIdAndId_PaintingId(customerId, paintingId);

	}
	
	

}

