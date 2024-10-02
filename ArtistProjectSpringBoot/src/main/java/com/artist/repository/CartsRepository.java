package com.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Carts;

public interface CartsRepository extends JpaRepository<Carts,String>{

}
