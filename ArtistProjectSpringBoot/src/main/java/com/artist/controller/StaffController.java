package com.artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.artist.dto.response.PaintingDTO;
import com.artist.dto.response.StaffDTO;
import com.artist.entity.Staff;
import com.artist.repository.StaffRepository;
import com.artist.service.impl.StaffServiceImpl;

@RestController
@RequestMapping("/StaffController")
public class StaffController {
	
	@Autowired
	StaffServiceImpl ssi;
	
	@Autowired
	StaffRepository sr;
	
	// 取得全部
	@RequestMapping(value = "/findall", method=RequestMethod.GET)
	public List<Staff> findall(Model model){
		return sr.findAll();
	}
	
	// 用staffId取得Staff
	@GetMapping(value = "/{staffId}")
	public ResponseEntity<Staff> findOneById(@PathVariable Integer staffId){
		Staff staff = ssi.getOneById(staffId);
//		System.out.println(art);
		return ResponseEntity.ok(staff);
	}
	
	// 新增
	@PostMapping(value = "/createStaff", consumes = "application/json")
	public ResponseEntity<?> createStaff(@RequestBody StaffDTO staffDTO) {
		try {
			ssi.create(staffDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("新增成功");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	// 修改
	@PutMapping(value ="/editStaff", consumes = "application/json")
    public ResponseEntity<?> updateStaff(@RequestBody StaffDTO staffDTO){
		ssi.update(staffDTO);
        return ResponseEntity.status(HttpStatus.OK).body("修改成功");
    }
	
	@DeleteMapping("/{staffId}")
	public ResponseEntity<Void> deleteStaffById(@PathVariable Integer staffId) {
		ssi.deleteByStaffId(staffId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
