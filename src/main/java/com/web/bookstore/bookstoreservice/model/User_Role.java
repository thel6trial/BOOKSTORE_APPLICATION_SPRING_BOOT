package com.web.bookstore.bookstoreservice.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity(name="user_role_seq")
public class User_Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id")
	public Integer roleId;
	
	@Size(min = 2, message="Name of role should be at least 2 characters")
	public String role_name;
	
	@OneToMany(mappedBy = "user_role")
	@JsonIgnore
	public List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public User_Role() {
		
	}

	public User_Role(Integer role_id, String role_name) {
		super();
		this.roleId = role_id;
		this.role_name = role_name;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer role_id) {
		this.roleId = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	@Override
	public String toString() {
		return "User_Role [role_id=" + roleId + ", role_name=" + role_name + "]";
	}
}
