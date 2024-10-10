package com.artist.service;

import java.util.List;

import com.artist.dto.response.ArtistDTO;
import com.artist.entity.Artist;

public interface ArtistService {

	//Create
	public void create(ArtistDTO artistDTO);
	//Read
	public String getArtistInfo();
	List<Artist> getAll();
	//Update
	void update(ArtistDTO artistDTO);

	//Delete
	void deleteArtist(Artist artist);
	void deleteByArtistId(String artistId);

}
