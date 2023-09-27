package com.web.bookstore.bookstoreservice.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.bookstore.bookstoreservice.model.Order_Book;

public interface Order_BookRepository extends JpaRepository<Order_Book, Integer>{

}
