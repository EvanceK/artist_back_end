package com.artist.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Paintings;


public interface PaintingsRepository extends JpaRepository<Paintings,String>{
 
}
