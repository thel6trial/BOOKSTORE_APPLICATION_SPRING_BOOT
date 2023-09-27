package com.web.bookstore.bookstoreservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.bookstore.bookstoreservice.model.User;

import jakarta.persistence.Id;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value = "SELECT * FROM user_seq WHERE username = :username", nativeQuery = true)
	public User findByUsername(String username);

}
