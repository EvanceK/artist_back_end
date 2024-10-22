package com.artist.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.artist.entity.Deliveryorders;

public interface DeliveryOrderRepository extends JpaRepository<Deliveryorders, String> {
    
  
    
    // 根據配送單查詢訂單
    @Query("SELECT d FROM Deliveryorders d WHERE d.deliveryNumber = :deliveryNumber")
    List<Deliveryorders> findByDeliveryNumber(@Param("deliveryNumber") String deliveryNumber);

    // 查詢某段時間內的配送訂單
    List<Deliveryorders> findByCreateDateBetween(LocalDateTime start, LocalDateTime end);

   

    // 查詢最新的配送訂單
    @Query("SELECT d FROM Deliveryorders d ORDER BY d.createDate DESC")
    List<Deliveryorders> findLatestDeliveryOrders();

    // 自定義查詢範例: 查詢某個配送員處理的所有配送訂單
    @Query("SELECT d FROM Deliveryorders d WHERE d.deliveryStaff = :staffId")
    List<Deliveryorders> findByDeliveryStaff(@Param("staffId") Integer staffId);

    // 自定義查詢範例: 查詢是誰包裝的
    @Query("SELECT d FROM Deliveryorders d WHERE d.packageStaff = :staffId")
    List<Deliveryorders> findByPackageStaff(@Param("staffId") Integer staffId);
}
