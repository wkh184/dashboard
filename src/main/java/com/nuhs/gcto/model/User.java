package com.nuhs.gcto.model;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user")
public class User {
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

	@Column(name="email")
	private String email;

	@Column(name="name")
	private String name;

	@Column(name="adid")
	private String adid;

	@Transient
	private String password;

	@OneToMany(mappedBy="userId", targetEntity=Role.class, fetch=FetchType.EAGER)
	private Collection<Role> roles; 

	//	@Transient
	//	private String[] roles;

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User(String adid, String password, String role) {
		super();
		this.adid = adid;
		this.password = password;
		//		this.roles = new String[1];
		//		roles[0] = role;
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

	public User() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		// TODO Auto-generated method stub
		//		String role = "";
		//		if("cih".equals(adid)) {
		//			role = "CORP";
		//		}else {
		//			role = "USER";
		//		}
		//		return role;
		//		String[] rolesStr = new String[roles.size()];
		StringBuffer sb = new StringBuffer();
		int count = 0;
		java.util.Iterator<Role> iterator = roles.iterator();
		while(iterator.hasNext()) {
			if(count > 0){
				sb.append(",");				
			}
			Role role = iterator.next();
			sb.append(role.getRole());
			count++;
		}
		return sb.toString();
	}


}
