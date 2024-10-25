package com.artist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.artist.dto.response.MyOrderResponse;
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
    
    
    // 根據客戶查詢訂單
    // Java 中，"""...""" 是 文本區塊（Text Block），這是一種用來處理多行字串的語法，於 Java 13 引入並在 Java 15 完整支援。
    @Query(value = """
    	    SELECT 
    	        o.customer_id,
    	        d.delivery_number,
    	        d.create_date,
    	        d.status,
    	        d.att_name,
    	        d.delivery_address,
    	        d.delivery_instrictions,
    	        d.total_amount,
    	        p.painting_id,
    	        p.painting_name,
    	        a.artist_name,
    	        p.image
    	    FROM deliveryorders d
    	    JOIN orders o ON d.delivery_number = o.delivery_number 
    	    JOIN orderdetails os ON o.order_number = os.order_number
    	    JOIN paintings p ON os.painting_id = p.painting_id 
    	    JOIN artist a ON a.artist_id = p.artist_id
    	    WHERE o.customer_id = :customerId
    	""",nativeQuery = true)
    List<Object[]> findByDeliveryNumberAndCustomer(@Param("customerId") String customerId);
 
    
}


