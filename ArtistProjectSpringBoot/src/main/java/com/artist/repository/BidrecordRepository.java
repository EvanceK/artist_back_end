package com.artist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Bidrecord;

public interface BidrecordRepository extends JpaRepository<Bidrecord,Long> {
	
	
  List<Bidrecord> findByPaintingIdOrderByBidAmountDesc(String paintingId); // where  paintingId = ?1 order by bidAmount=?2 desc

}
