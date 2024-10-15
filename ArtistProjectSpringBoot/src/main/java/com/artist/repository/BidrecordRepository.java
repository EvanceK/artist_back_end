package com.artist.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.artist.entity.Bidrecord;

import jakarta.persistence.Tuple;

public interface BidrecordRepository extends JpaRepository<Bidrecord,Long> {
	
	
  List<Bidrecord> findByPaintingIdOrderByBidAmountDesc(String paintingId); // where  paintingId = ?1 order by bidAmount=?2 desc

  List<Bidrecord> findByBidderIdOrderByBidTimeDesc(String bidderId); // where  bidderId = ?1 order by bidTime desc

  List<Bidrecord> findByBidderIdAndDepositStatusOrderByBidTime(String bidderId, String depositStatus);
  
  List<Bidrecord> findByPaintingId(String paintingId); 
  
  
	@Query("SELECT b.paintingId AS paintingId, COUNT(b.paintingId) AS paintingCount " +
		       "FROM Bidrecord b "+
		       "JOIN Paintings p ON b.paintingId = p.paintingId " +
	           "WHERE p.delicated >= 1 " +
		       "GROUP BY b.paintingId ORDER BY COUNT(b.paintingId) DESC")
		List<Tuple> findTopBiddingWithLimit(Pageable pageable);

}
