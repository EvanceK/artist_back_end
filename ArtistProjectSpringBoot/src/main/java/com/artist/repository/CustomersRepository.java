package com.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Customers;

public interface CustomersRepository extends JpaRepository<Customers,String>{

}
