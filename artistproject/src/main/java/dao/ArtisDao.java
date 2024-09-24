package dao;

import java.util.List;

import bean.Artis;

public interface ArtisDao {

	// Create
	void create(Artis art);

	// Read
	List<Artis> selectAll();

	// Update
	void update(Artis art);

	// Delete
	void delete(String id);
}
