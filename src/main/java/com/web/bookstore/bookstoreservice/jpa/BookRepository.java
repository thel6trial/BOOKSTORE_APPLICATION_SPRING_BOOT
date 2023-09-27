package com.web.bookstore.bookstoreservice.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.bookstore.bookstoreservice.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	public List<Book> findByNameContainingIgnoreCase(String name);
}
