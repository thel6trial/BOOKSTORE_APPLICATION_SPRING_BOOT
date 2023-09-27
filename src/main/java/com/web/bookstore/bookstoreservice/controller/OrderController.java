package com.web.bookstore.bookstoreservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.web.bookstore.bookstoreservice.jpa.BookRepository;
import com.web.bookstore.bookstoreservice.jpa.OrderRepository;
import com.web.bookstore.bookstoreservice.jpa.Order_BookRepository;
import com.web.bookstore.bookstoreservice.jpa.UserRepository;
import com.web.bookstore.bookstoreservice.model.Book;
import com.web.bookstore.bookstoreservice.model.Order;
import com.web.bookstore.bookstoreservice.model.Order_Book;
import com.web.bookstore.bookstoreservice.model.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class OrderController {

    @Autowired
    private Order_BookRepository order_bookRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private BookRepository bookRepository;

    
    
    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
    	
    	int total_price = 0;
    	
    	// Retrieve the order from the database
	    Order order = orderRepository.findBy0();
	    
	    int order_id = order.getOrderId();
    	    	
    	List<Order_Book> orderBooks = order.getOrder_books();
    	    
    	for(Order_Book orderBook: orderBooks) {
    	    Book book = orderBook.getBook();
    	    total_price += calculatePrice(orderBook.getQuantity(), book.getPrice());
    	}
    	    
    	order.setTotal_price(total_price);
    	orderRepository.save(order);
    	
    	model.addAttribute("order", order);
    	    
        // Return a success response
        return "cart";
    }
   

    private int calculatePrice(int quantity, double pricePerItem) {
        return (int) (pricePerItem * quantity);
    }
}
