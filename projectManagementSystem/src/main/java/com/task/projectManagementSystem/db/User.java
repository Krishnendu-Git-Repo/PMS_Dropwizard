package com.task.projectManagementSystem.db;

import java.security.Principal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
//@NamedQueries({
//	@NamedQuery(name = "com.task.projectManagementSystem.db.User.findByUserName",
//            query = "select u from User u")
//})
public class User implements Principal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userName;
	private String password;
	private String roles;	
	
	public User() {
		super();
	}

	public User(Long id, String userName, String password, String roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", roles=" + roles + "]";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return userName;
	}

}
