package com.artist.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artist.dto.PaintingDTO;
import com.artist.dto.WishlistDTO;
import com.artist.entity.Wishlist;
import com.artist.repository.WishlistRepository;
import com.artist.service.WishlistService;


@Service
public class WishlistServiceImpl implements WishlistService{
	
	  @Autowired
	    private WishlistRepository wr;
	  @Autowired
	    private PaintingsServiceImpl psi;

	@Override
	public List<WishlistDTO> findAllWishlistWithPaintings(String customerId) {
		List<WishlistDTO> wishlistDTOLsit = new ArrayList<WishlistDTO>();
		WishlistDTO wishlistDTO = new WishlistDTO();
		List<Wishlist> allwish = wr.findByCustomerId(customerId);
		for(Wishlist w:allwish) {
			String paintingId = w.getPaintingId();
			PaintingDTO paintings = psi.getByPaintingsId(paintingId);
			 wishlistDTO.setPaintingName(paintings.getPaintingName());
			 wishlistDTO.setArtisName(paintings.getArtisName());
			 wishlistDTO.setPrice(paintings.getPrice());
			 wishlistDTO.setSmallUrl(paintings.getSmallUrl());
			 wishlistDTOLsit.add(wishlistDTO);
		}
		return wishlistDTOLsit;
	}

	@Override
	public void delete(String paintingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToWishlist(String customerId, String paintingId) {
		Wishlist wishlist = new Wishlist(customerId,paintingId);
		wr.save(wishlist);		
	}
	
	
	

}
