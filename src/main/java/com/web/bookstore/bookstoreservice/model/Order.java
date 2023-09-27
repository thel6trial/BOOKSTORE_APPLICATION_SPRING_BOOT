package com.web.bookstore.bookstoreservice.model;

import java.time.LocalDateTime;
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

@Entity(name="order_seq")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int orderId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	private int total_price;
	
	
	@OneToMany(mappedBy = "order")
	@JsonIgnore
	private List<Order_Book> order_books;

	public Order(int orderId, User user, int total_price, List<Order_Book> order_books) {
		super();
		this.orderId = orderId;
		this.user = user;
		this.total_price = total_price;
		this.order_books = order_books;
	}

	public List<Order_Book> getOrder_books () {
		return order_books;
	}

	public void setOrder_books(List<Order_Book> order_books) {
		this.order_books = order_books;
	}

	public Order(int order_id, int total_price) {
		super();
		this.orderId = order_id;
		this.total_price = total_price;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int order_id) {
		this.orderId = order_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + orderId + ", user=" + user + ", time=" + total_price + "]";
	}
}
