package com.artist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.artist.dto.response.DeliveryOrderResponseDTO;
import com.artist.entity.DeliveryOrders;



public interface DeliveryOrdersRepository extends JpaRepository<DeliveryOrders, String> {
    
	
	@Query("SELECT d FROM DeliveryOrders d " +
		       "JOIN d.orders o " +
		       "JOIN o.orderDetail od")
	List<DeliveryOrders> findAllWithOrdersAndDetails();
	

//    // 根據配送單查詢訂單
	@Query("SELECT d FROM DeliveryOrders d " +
		       "JOIN d.orders o " +
		       "JOIN o.orderDetail od " +
		       "WHERE d.deliveryNumber = :deliveryNumber")
	Optional<DeliveryOrders> findByDeliveryNumberWithOrdersAndDetails(@Param("deliveryNumber") String deliveryNumber);    
//        
    //用狀態去查哪張deliveryorders還沒處理
//    @Query("SELECT d FROM DeliveryOrders d "+
//    					" JOIN d.orders o " +
//    					" JOIN o.orderDetail od " + 
//    					"WHERE d.status = :status")
//    List<DeliveryOrders> findByStatus(@Param("status") String status);
//    //List<DeliveryOrderResponseDTO> findByStatus(@Param("status") String status);
//
//	Optional<DeliveryOrders> findByDeliveryNumberWithOrdersAndDetails1(@Param("deliveryNumber") String deliveryNumber);    
//    
	// 根據狀態查詢訂單
	@Query("SELECT d FROM DeliveryOrders d " +
		       "JOIN d.orders o " +
		       "JOIN o.orderDetail od " +
		       "WHERE d.status = :status")
	List<DeliveryOrders> findByStatusWithOrdersAndDetails(@Param("status") String status);    


    // 自定義查詢範例: 查詢某個配送員處理的所有配送訂單
	@Query(nativeQuery = true, value = "SELECT s.staff_name FROM deliveryorders d join staff s on  d.delivery_staff=s.staff_username where d.delivery_staff=:staffId")
    String findByDeliveryStaff(@Param("staffId") String staffId);
//
//    
    // 自定義查詢範例: 查詢是誰包裝的
    @Query(nativeQuery = true, value = "SELECT s.staff_name FROM deliveryorders d join staff s on  d.package_staff=s.staff_username where d.package_staff=:staffId")
    String findByPackageStaff(@Param("staffId") String staffId);
}


