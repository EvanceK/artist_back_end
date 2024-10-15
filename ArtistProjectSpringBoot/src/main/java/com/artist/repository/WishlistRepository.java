package com.artist.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.artist.entity.Wishlist;
import com.artist.entity.WishlistId;

import jakarta.persistence.Tuple;

public interface WishlistRepository extends JpaRepository<Wishlist, WishlistId> {
	// 根據 customerId 查詢所有 Wishlist
	List<Wishlist> findAllById_CustomerId(String customerId);

	// 根據 customerId 和 paintingId 刪除 Wishlist
	void deleteById_CustomerIdAndId_PaintingId(String customerId, String paintingId);

	boolean existsById_CustomerIdAndId_PaintingId(String customerId, String paintingId);

	
	   @Query("SELECT w.id.paintingId AS paintingId, COUNT(w.id.paintingId) AS paintingCount " +
	           "FROM Wishlist w " +
	           "JOIN Paintings p ON w.id.paintingId = p.id " +
	           "WHERE p.delicated = 1 " +
	           "GROUP BY w.id.paintingId " +
	           "ORDER BY COUNT(w.id.paintingId) DESC")
	   List<Tuple> findTopFavoritesWithLimit(Pageable pageable);
//	PageRequest.of(0, 3) 表示查詢結果的第 1 頁，每頁顯示 3 條記錄。	

}