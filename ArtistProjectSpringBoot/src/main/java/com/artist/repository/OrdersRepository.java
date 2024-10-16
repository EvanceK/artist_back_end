package com.artist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.artist.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, String>{

//    List<Orders> findByCustomerId(String customerId);
    
	@Query("SELECT o FROM Orders o WHERE o.orderNumber = :orderNumber")
	Optional<Orders> findByOrderNumber(@Param("orderNumber") String orderNumber);
    
    @EntityGraph(attributePaths = {"orderDetail.painting"})
    List<Orders> findByCustomerId(String customerId);
    
}
