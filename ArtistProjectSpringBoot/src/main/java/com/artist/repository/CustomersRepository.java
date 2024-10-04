package com.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.artist.entity.Customers;
import java.util.List;
import java.util.Optional;

public interface CustomersRepository extends JpaRepository<Customers, String> {

	Optional<Customers> findByEmail(String email);

	boolean existsByEmail(String email);

//	@Query("SELECT c FROM customers c")
//	List<Customers> findAll();

}
