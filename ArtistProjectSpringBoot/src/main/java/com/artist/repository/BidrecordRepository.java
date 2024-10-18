package com.artist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.artist.dto.response.PaintingDTO;
import com.artist.entity.Bidrecord;
import com.artist.entity.Paintings;

public interface BidrecordRepository extends JpaRepository<Bidrecord,Long> {
	
	
  List<Bidrecord> findByPaintingIdOrderByBidAmountDesc(String paintingId); // where  paintingId = ?1 order by bidAmount=?2 desc

  List<Bidrecord> findByBidderIdOrderByBidTimeDesc(String bidderId); // where  bidderId = ?1 order by bidTime desc

  List<Bidrecord> findByBidderIdAndDepositStatusOrderByBidTime(String bidderId, String depositStatus);
  
  List<Bidrecord> findByPaintingId(String paintingId); 
  
	
	@Query(value = "SELECT b.painting_id AS paintingId, COUNT(b.painting_id) AS paintingCount " +
            "FROM bidrecord b " +
            "JOIN paintings p ON b.painting_id = p.painting_id " +
            "WHERE p.upload_date > NOW() - INTERVAL :canbidday DAY " +
            "GROUP BY b.painting_id " +
            "ORDER BY COUNT(b.painting_id) DESC " +
            "LIMIT :limit", 
    nativeQuery = true)
	List<Object[]> findTopBiddingWithLimit(@Param("canbidday") int canbidday, @Param("limit") int limit);



}
