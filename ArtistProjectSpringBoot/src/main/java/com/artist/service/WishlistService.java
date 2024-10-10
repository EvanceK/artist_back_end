package com.artist.service;

import java.util.List;

import com.artist.dto.response.WishlistDTO;

public interface WishlistService {
	// Create
    void addToWishlist(String customerId, String paintingId);
    // Read
	List<WishlistDTO> findAllWishlistWithPaintings(String customerId);
    // Delete
	void deleteFromWishlist(String customerId,String paintingId);
	
	boolean existsBycustomerIdAndpaintingId(String customerId, String paintingId);

}
