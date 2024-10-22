package com.artist.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.artist.entity.DeliveryOrders;

public interface DeliveryOrdersRepository extends JpaRepository<DeliveryOrders, String>{

    
    // 根據配送單查詢訂單
    @Query("SELECT d FROM DeliveryOrders d WHERE d.deliveryNumber = :deliveryNumber")
    List<DeliveryOrders> findByDeliveryNumber(@Param("deliveryNumber") String deliveryNumber);

    // 查詢某段時間內的配送訂單
    List<DeliveryOrders> findByCreateDateBetween(LocalDateTime start, LocalDateTime end);

   

//    // 查詢最新的配送訂單
    @Query("SELECT d FROM DeliveryOrders d ORDER BY d.createDate DESC")
    List<DeliveryOrders> findLatestDeliveryOrders();

    // 自定義查詢範例: 查詢某個配送員處理的所有配送訂單
    @Query("SELECT d FROM DeliveryOrders d WHERE d.deliveryStaff = :staffId")
    List<DeliveryOrders> findByDeliveryStaff(@Param("staffId") Integer staffId);

    // 自定義查詢範例: 查詢是誰包裝的
    @Query("SELECT d FROM DeliveryOrders d WHERE d.packageStaff = :staffId")
    List<DeliveryOrders> findByPackageStaff(@Param("staffId") Integer staffId);
}
