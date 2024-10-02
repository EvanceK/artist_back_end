package com.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Wishlist;

public interface WishlistRepository  extends JpaRepository<Wishlist, String>{

}
