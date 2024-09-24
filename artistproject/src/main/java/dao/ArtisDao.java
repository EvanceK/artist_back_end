package dao;

import java.util.List;

import bean.Artis;

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
