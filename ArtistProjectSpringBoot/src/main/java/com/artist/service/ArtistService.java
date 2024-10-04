package com.artist.service;

import com.artist.dto.ArtistDTO;
import com.artist.entity.Artist;

public interface ArtistService {

	//Create
	public void create(ArtistDTO artistDTO);
	//Read
	public String getArtistInfo();
	//Update
	void update(ArtistDTO artistDTO);

	//Delete
	void deleteArtist(Artist artist);
	void deleteByArtistId(String artistId);

}
