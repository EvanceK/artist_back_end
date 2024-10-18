package com.artist.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.artist.entity.Wishlist;
import com.artist.entity.WishlistId;

import jakarta.persistence.Tuple;

public interface WishlistRepository extends JpaRepository<Wishlist, WishlistId> {
	// 根據 customerId 查詢所有 Wishlist
	List<Wishlist> findAllById_CustomerId(String customerId);

	// 根據 customerId 和 paintingId 刪除 Wishlist
	void deleteById_CustomerIdAndId_PaintingId(String customerId, String paintingId);

	boolean existsById_CustomerIdAndId_PaintingId(String customerId, String paintingId);

	//PageRequest.of(0, 3) 表示查詢結果的第 1 頁，每頁顯示 3 條記錄。	
//	//無登入用
//	   @Query("SELECT w.id.paintingId AS paintingId, COUNT(w.id.paintingId) AS paintingCount " +
//	           "FROM Wishlist w " +
//	           "JOIN Paintings p ON w.id.paintingId = p.id " +
//	           "WHERE p.delicated > 0 "+
//	           "GROUP BY w.id.paintingId " +
//	           "ORDER BY COUNT(w.id.paintingId) DESC")
//	   List<Tuple> findTopFavoritesWithLimit(Pageable pageable);
//	   
//	//有登入用，根據顧客沒選的推薦   
//	   @Query("SELECT w.id.paintingId AS paintingId, COUNT(w.id.paintingId) AS paintingCount " +
//	           "FROM Wishlist w " +
//	           "JOIN Paintings p ON w.id.paintingId = p.id " +
//	           "WHERE p.delicated > 0 AND w.id.paintingId NOT IN " +
//	           "(SELECT wl.id.paintingId FROM Wishlist wl WHERE wl.id.customerId = :customerId) " +
//	           "GROUP BY w.id.paintingId " +
//	           "ORDER BY COUNT(w.id.paintingId) DESC")
//	   List<Tuple> findTopFavoritesWithLimit(@Param("customerId") String customerId, Pageable pageable);

	   
		//無登入用
	   @Query(value = "SELECT w.painting_id AS paintingId, COUNT(w.painting_id) AS paintingCount " +
               "FROM wishlist w " +
               "JOIN paintings p ON w.painting_id = p.painting_id " +
               "WHERE p.upload_date > NOW() - INTERVAL :totalDay DAY " +
               "GROUP BY w.painting_id " +
               "ORDER BY COUNT(w.painting_id) DESC", nativeQuery = true)
	   List<Tuple> findTopFavoritesWithLimit(Pageable pageable,@Param("totalDay") int totalDay);
	   
	   
	//有登入用，根據顧客沒選的推薦   
	   @Query(value = "SELECT w.painting_id AS paintingId, COUNT(w.painting_id) AS paintingCount " +
               "FROM wishlist w " +
               "JOIN paintings p ON w.painting_id = p.painting_id " +
               "WHERE p.upload_date > NOW() - INTERVAL :totalDay DAY " +
               "AND w.painting_id NOT IN " +
               "(SELECT wl.painting_id FROM wishlist wl WHERE wl.customer_id = :customerId) " +
               "GROUP BY w.painting_id " +
               "ORDER BY COUNT(w.painting_id) DESC", nativeQuery = true)
	   List<Tuple> findTopFavoritesWithLimit(@Param("customerId") String customerId, Pageable pageable,@Param("totalDay") int totalDay);
	   
}