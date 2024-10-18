package com.artist.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artist.dto.response.StaffDTO;
import com.artist.entity.Staff;
import com.artist.repository.StaffRepository;
import com.artist.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffRepository sr;
	
	@Override
	public void create(StaffDTO staffDTO) {
		Staff staff = new Staff();
		staff.setStaffName(staffDTO.getStaffName());
		staff.setStaffDepartment(staffDTO.getStaffDepartment());
		staff.setStaffUsername(staffDTO.getStaffUsername());
		staff.setStaffPassword(staffDTO.getStaffPassword());
		
		sr.save(staff);
	}

	@Override
	public List<Staff> getAll() {
		return sr.findAll();
	}

	@Override
	public Staff getOneById(Integer staffId) {
		Optional<Staff> byId = sr.findById(staffId);
		Staff staff = byId.get();
		return staff;
	}

	@Override
	public void update(StaffDTO staffDTO) {
		Integer staffId = staffDTO.getStaffId();
		if (staffId != null) {
			Staff staff = new Staff();
			staff.setStaffId(staffDTO.getStaffId());
			staff.setStaffDepartment(staffDTO.getStaffDepartment());
			staff.setStaffName(staffDTO.getStaffName());
			staff.setStaffUsername(staffDTO.getStaffUsername());
			staff.setStaffPassword(staffDTO.getStaffPassword());
		}else {
			System.out.println("data not fond");
		}
		
	}

	@Override
	public void deleteByStaffId(Integer staffId) {
		sr.deleteById(staffId);
	}

}
