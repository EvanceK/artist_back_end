package com.artist.dto.response;

public class StaffDTO {
	
	private int staff_id;
	private String staffName;
	private String staffDepartment;
	private String staffUsername;
	private String staffPassword;
	public StaffDTO() {
		super();
	}
	public StaffDTO( String staffName, String staffDepartment, 
			String staffUsername,String staffPassword) {
		super();
		this.staffName = staffName;
		this.staffDepartment = staffDepartment;
		this.staffUsername = staffUsername;
		this.staffPassword = staffPassword;
	}


	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
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
