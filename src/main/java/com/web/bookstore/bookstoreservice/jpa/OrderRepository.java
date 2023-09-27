package com.web.bookstore.bookstoreservice.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.web.bookstore.bookstoreservice.model.Order;

import jakarta.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	@Query(value = "SELECT * FROM order_seq WHERE total_price = 0", nativeQuery = true)
	public Order findBy0();
	
	public Order findByOrderId(int orderId);

	@Query(value = "SELECT * FROM order_seq WHERE total_price != 0", nativeQuery = true)
	public List<Order> findByDiff0();
	
	@Transactional
	@Modifying
    @Query("DELETE FROM order_seq o WHERE o.total_price = 0")
    public void deleteBy0();

}
