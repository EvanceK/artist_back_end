package com.artist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Customers;

public interface CustomersRepository extends JpaRepository<Customers, String> {

	Optional<Customers> findByEmail(String email);

	boolean existsByEmail(String email);

	

}
