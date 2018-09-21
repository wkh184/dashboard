package com.nuhs.gcto.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="issues")
public class Issue implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="created_datetime")
	private Timestamp createdDT;

	@Column(name="updated_datetime")
	private Timestamp updatedDT;
	
	@Column(name="created_by")
	private String createdBy = "system";
	
	@Column(name="updated_by")
	private String updatedBy = "system";

	@Column(name="title")
	private String title;

	@Column(name="details")
	private String details;

	@Column(name="user")
	private String user;

	@Column(name="department")
	private String department;

	@Column(name="category")
	private String category;


	public Issue() {
		super();
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedDT() {
		return createdDT;
	}

	public void setCreatedDT(Timestamp createdDT) {
		this.createdDT = createdDT;
	}

	public Timestamp getUpdatedDT() {
		return updatedDT;
	}

	public void setUpdatedDT(Timestamp updatedDT) {
		this.updatedDT = updatedDT;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


}
