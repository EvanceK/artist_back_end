package com.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, String>{

	

//	  List<Bidrecord> findByCustomerId(String customerId); 
//	  List<Bidrecord> findByorderNumber(String orderNumber);
//
//	  List<Bidrecord> findByBidderIdOrderByBidTimeDesc(String bidderId); // where  bidderId = ?1 order by bidTime desc
//
//	  List<Bidrecord> findByBidderIdAndDepositStatusOrderByBidTimeDesc(String bidderId, String depositStatus);

}
