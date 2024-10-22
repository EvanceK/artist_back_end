package com.artist.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.artist.dto.request.DeliveryOrderRequestDTO;
import com.artist.dto.response.DeliveryOrdersDTO;
import com.artist.entity.DeliveryOrders;

public interface DeliveryOrdersService {

	// create
	public void create(DeliveryOrdersDTO deliveryOrdersfDTO);

	// update
	public void update(DeliveryOrdersDTO deliveryOrdersfDTO);

	String createDeliveryOrder(DeliveryOrderRequestDTO deliveryOrderRequestDTO);
	
	// 根據訂單號查詢配送訂單
    Optional<DeliveryOrders> findByOrderNumber(String orderNumber);
    
    //根據配送單查詢訂單
    @Query("SELECT d FORM Deliveryorders d WHERE d.delivernumber = :deliverynumber")
    List<DeliveryOrders> findByDeliveryNumber(@Param("deliveryNumber") String deliveryNumber);

    // 查詢某段時間內的配送訂單
    List<DeliveryOrders> findByCreateDateBetween(LocalDateTime start, LocalDateTime end);

    // 檢查某訂單是否存在
    boolean existsByOrderNumber(String orderNumber);

    // 查詢最新的配送訂單
    @Query("SELECT d FROM Deliveryorders d ORDER BY d.createDate DESC")
    List<DeliveryOrders> findLatestDeliveryOrders();

    // 自定義查詢範例: 查詢某個配送員處理的所有配送訂單
    @Query("SELECT d FROM Deliveryorders d WHERE d.deliverystaff = :staffId")
    List<DeliveryOrders> findByDeliveryStaff(@Param("staffId") Integer staffId);
    
    // 自定義查詢範例: 查詢是誰包裝的
    @Query("SELECT d FROM Deliveryorders d WHERE d.packagestaff = :staffId")
    List<DeliveryOrders> findByPackageStaff(@Param("staffId") Integer staffId);


}
