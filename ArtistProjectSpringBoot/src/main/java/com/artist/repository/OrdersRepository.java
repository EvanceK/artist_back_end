package com.artist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, String>{

//    List<Orders> findByCustomerId(String customerId);
    
    
    @EntityGraph(attributePaths = {"orderDetail.painting"})
    List<Orders> findByCustomerId(String customerId);
    
}
