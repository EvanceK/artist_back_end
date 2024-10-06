package com.artist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.artist.entity.Wishlist;

public interface WishlistRepository  extends JpaRepository<Wishlist, String>{
	
	List<Wishlist> findAllByCustomerId(String customerId);

	void deleteByCustomerIdAndPaintingId(String customerId,String paintingId);

}
