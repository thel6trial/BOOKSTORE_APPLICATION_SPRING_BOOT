package com.web.bookstore.bookstoreservice.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity(name="user_seq")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	List<Order> orders;
	
	@Size(min = 2, message="Name of user should be at least 2 characters")
	public String username;
	
	@Size(min = 2, message="Password should be at least 2 characters")
	private String password;
	
	private String pay_method;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	@JsonIgnore
	private User_Role user_role;

	public User_Role getUser_role() {
		return user_role;
	}

	public void setUser_role(User_Role user_role) {
		this.user_role = user_role;
	}
	
	public User() {
		
	}

	public User(Integer user_id,
			String username, String password, String pay_method) {
		super();
		this.userId = user_id;
		this.username = username;
		this.password = password;
		this.pay_method = pay_method;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer user_id) {
		this.userId = user_id;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}	
}
