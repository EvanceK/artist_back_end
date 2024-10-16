package com.artist.service;

import java.util.List;

import com.artist.dto.response.TopFavoritesDTO;
import com.artist.dto.response.WishlistDTO;

public interface WishlistService {
	// Create
    void addToWishlist(String customerId, String paintingId);
    // Read
	List<WishlistDTO> findAllWishlistWithPaintings(String customerId);
	List<TopFavoritesDTO> getTopFavorites(String customerId,int size);
	List<TopFavoritesDTO> getTopFavorites(int size);

    // Delete
	void deleteFromWishlist(String customerId,String paintingId);
	
	boolean existsBycustomerIdAndpaintingId(String customerId, String paintingId);

}
