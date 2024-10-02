package com.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String>{

}
