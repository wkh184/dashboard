package com.nuhs.gcto.model;

public class Patient {
	private String attendingDoctor;
	private String location;
	private String gender;
	private String dob;
	
	public String getAttendingDoctor() {
		return attendingDoctor;
	}
	public void setAttendingDoctor(String attendingDoctor) {
		this.attendingDoctor = attendingDoctor;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	
}
