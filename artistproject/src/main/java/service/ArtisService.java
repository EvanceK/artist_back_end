package service;

import java.util.List;

import bean.Artis;

public interface ArtisService {
	
	//create
	void create(String artName);
	//read
	
	List<Artis> selectAll();
	Artis selectByArtisId(String artId);
	Artis selectByArtisName(String artName);
	
	//update
	
	void update(String artId, String artName);
	
	//delete
	void delete(String artId);
}
