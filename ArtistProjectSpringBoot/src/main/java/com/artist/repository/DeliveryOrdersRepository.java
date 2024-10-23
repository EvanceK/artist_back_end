package com.artist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.artist.entity.DeliveryOrders;



public interface DeliveryOrdersRepository extends JpaRepository<DeliveryOrders, String> {
    
	
	@Query("SELECT d FROM DeliveryOrders d " +
		       "JOIN d.orders o " +
		       "JOIN o.orderDetail od")
	List<DeliveryOrders> findAllWithOrdersAndDetails();
	
	
    // 根據配送單查詢訂單
	@Query("SELECT d FROM DeliveryOrders d " +
		       "JOIN d.orders o " +
		       "JOIN o.orderDetail od " +
		       "WHERE d.deliveryNumber = :deliveryNumber")
		Optional<DeliveryOrders> findByDeliveryNumberWithOrdersAndDetails(@Param("deliveryNumber") String deliveryNumber);    
    
	// 根據狀態查詢訂單
		@Query("SELECT d FROM DeliveryOrders d " +
			       "JOIN d.orders o " +
			       "JOIN o.orderDetail od " +
			       "WHERE d.status = :status")
		List<DeliveryOrders> findByStatusWithOrdersAndDetails(@Param("status") String status);    
	    
    
    // 自定義查詢範例: 查詢某個配送員處理的所有配送訂單
    @Query("SELECT d FROM DeliveryOrders d WHERE d.deliveryStaff = :staffId")
    List<DeliveryOrders> findByDeliveryStaff(@Param("staffId") Integer staffId);

    
    // 自定義查詢範例: 查詢是誰包裝的
    @Query("SELECT d FROM DeliveryOrders d WHERE d.packageStaff = :staffId")
    List<DeliveryOrders> findByPackageStaff(@Param("staffId") Integer staffId);
}


