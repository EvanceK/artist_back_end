package com.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Inventories;

public interface InventoriesRepository extends JpaRepository<Inventories, String>{

}
