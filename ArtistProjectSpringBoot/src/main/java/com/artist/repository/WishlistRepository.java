package com.artist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Wishlist;

public interface WishlistRepository  extends JpaRepository<Wishlist, String>{
	
	List<Wishlist> findByCustomerId(String customerId);

}
