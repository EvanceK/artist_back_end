package com.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artist.entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist,String> {

//	@Query("SELECT a FROM Artist a JOIN a.paintings p WHERE p.delicated = true")
//	List<Artist> findAllDelicatedArtistWithPaintings();
//	
//	boolean existsByArtistId(String artistId);
    
	
}
