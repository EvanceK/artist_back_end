package com.artist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff")
public class Staff {
	
	@Id
	@GeneratedValue
	@Column(name="artist_id")
	private Integer staffId;
	
	@Column(name="staff_name")
	private String staffName;
	
	@Column(name="staff_department")
	private String staffDepartment;
	
	@Column(name="staff_username")
	private String staffUsername;
	
	@Column(name="staff_password")
	private String staffPassword;

	public Staff() {
		super();
	}

	public Staff(Integer staffId, String staffName, 
			String staffDepartment, String staffUsername,
			String staffPassword) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffDepartment = staffDepartment;
		this.staffUsername = staffUsername;
		this.staffPassword = staffPassword;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffDepartment() {
		return staffDepartment;
	}

	public void setStaffDepartment(String staffDepartment) {
		this.staffDepartment = staffDepartment;
	}

	public String getStaffUsername() {
		return staffUsername;
	}

	public void setStaffUsername(String staffUsername) {
		this.staffUsername = staffUsername;
	}

	public String getStaffPassword() {
		return staffPassword;
	}

	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}
	
	
	
}
