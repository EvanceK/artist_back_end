package com.artist.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Paintings;


public interface PaintingsDao extends JpaRepository<Paintings,String>{
 
}
