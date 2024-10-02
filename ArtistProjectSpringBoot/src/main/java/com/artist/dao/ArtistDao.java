package com.artist.dao;

import java.util.List;

import com.artist.entity.Artist;

public interface ArtistDao {

	// Create
	void create(String artName);

	// Read
	List<Artist> selectAll();

	// Update
	void update(Artist art);

	// Delete
	void delete(String id);
}
