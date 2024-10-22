package com.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.artist.entity.DeliveryOrders;

public interface DeliveryOrdersRepository extends JpaRepository<DeliveryOrders, String>{

}
