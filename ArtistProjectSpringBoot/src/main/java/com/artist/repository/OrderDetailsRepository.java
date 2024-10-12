package com.artist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String>{

	  Optional<OrderDetails> findByPaintingId(String paintingId);

	  Optional<OrderDetails> findByOrderNumber(String orderNumber);

}
