package com.artist.service;

import java.util.List;

import com.artist.dto.response.StaffDTO;
import com.artist.entity.Staff;

public interface StaffService {
	
	//Create
	public void create(StaffDTO staffDTO);
	
	//Read
	public List<?> getAll();
	public Staff getOneById(Integer staffId);
	
	//Update
	public void update(StaffDTO staffDTO);
	
	//Delete
	public void deleteByStaffId(Integer staffId);
	
}
