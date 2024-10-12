package com.artist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String>{

	  List<OrderDetails> findByPaintingId(String paintingId);

	  List<OrderDetails> findByOrderNumber(String orderNumber);

}
