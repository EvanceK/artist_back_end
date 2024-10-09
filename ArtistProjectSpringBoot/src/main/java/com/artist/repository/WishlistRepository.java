package com.artist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Wishlist;
import com.artist.entity.WishlistId;

public interface WishlistRepository extends JpaRepository<Wishlist, WishlistId> {
	// 根據 customerId 查詢所有 Wishlist
	List<Wishlist> findAllById_CustomerId(String customerId);

	// 根據 customerId 和 paintingId 刪除 Wishlist
	void deleteById_CustomerIdAndId_PaintingId(String customerId, String paintingId);

	boolean existsById_CustomerIdAndId_PaintingId(String customerId, String paintingId);

	
}