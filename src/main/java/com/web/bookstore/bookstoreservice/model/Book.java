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

@Entity(name="book_seq")
public class Book {
	
	protected Book() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id")
	private Integer bookId;
	
	@Size(min = 2, message="Name of book should be at least 2 characters")
	private String name;
	
	@Size(min = 2, message="Name of author should be at least 2 characters")
	private String author;
	
	private String publisher;
	private Integer price;
	
	@OneToMany(mappedBy = "book")
	@JsonIgnore
	private List<Order_Book> order_books;
	
	public List<Order_Book> getOrder_books() {
		return order_books;
	}

	public void setOrder_books(List<Order_Book> order_books) {
		this.order_books = order_books;
	}

	public Book(Integer id, String name, String author, String publisher, Integer price) {
		super();
		this.bookId = id;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer id) {
		this.bookId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [id=" + bookId + ", name=" + name + ", author=" + author + ", publisher=" + publisher + ", price="
				+ price + "]";
	}
}
