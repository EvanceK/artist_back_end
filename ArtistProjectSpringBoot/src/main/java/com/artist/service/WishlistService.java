package com.artist.service;

import java.util.List;

import com.artist.dto.WishlistDTO;

public interface WishlistService {
	// Create
    void addToWishlist(String customerId, String paintingId);
    // Read
	List<WishlistDTO> findAllWishlistWithPaintings(String customerId);
    // Delete
    void delete(String paintingId);
}
