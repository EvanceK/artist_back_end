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
	// 查詢單筆訂單
    @Query("SELECT o FROM Orders o WHERE o.orderNumber = :orderNumber")
    Optional<Orders> findByOrderNumber(@Param("orderNumber") String orderNumber);

    // 查詢某個客戶的所有訂單
//    @EntityGraph(attributePaths = {"orderDetail.painting"})
    @Query(value = "SELECT * FROM `artistproject`.`orders` WHERE customer_id = :customerId and delivery_number IS NULL", nativeQuery = true)
    List<Orders> findByCustomerId(String customerId);

    // 新增查詢多筆訂單的方法
    @Query("SELECT o FROM Orders o WHERE o.orderNumber IN :orderNumbers")
    List<Orders> findByOrderNumbers(@Param("orderNumbers") List<String> orderNumbers);
    
}
