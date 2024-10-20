package com.artist.service;

import java.util.List;

import com.artist.dto.response.ArtistDTO;
import com.artist.entity.Artist;

public interface ArtistService {

	//Create
	public void create(ArtistDTO artistDTO);
	
	//Read
	public String getArtistInfo();
	public List<Artist> getAll();
	public Artist getOneById(String artistId);
	
	//Update
	void update(ArtistDTO artistDTO);

	//Delete
	void deleteArtist(Artist artist);
	void deleteByArtistId(String artistId);

}
