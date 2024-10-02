package com.artist.dao;

import java.util.List;

import com.artist.entity.Artis;

public interface ArtisDao {

	// Create
	void create(String artName);

	// Read
	List<Artis> selectAll();

	// Update
	void update(Artis art);

	// Delete
	void delete(String id);
}
