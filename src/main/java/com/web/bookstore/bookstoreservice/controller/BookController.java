package com.web.bookstore.bookstoreservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.web.bookstore.bookstoreservice.jpa.BookRepository;
import com.web.bookstore.bookstoreservice.jpa.OrderRepository;
import com.web.bookstore.bookstoreservice.jpa.Order_BookRepository;
import com.web.bookstore.bookstoreservice.jpa.UserRepository;
import com.web.bookstore.bookstoreservice.jpa.User_RoleRepository;
import com.web.bookstore.bookstoreservice.model.Book;
import com.web.bookstore.bookstoreservice.model.Order;
import com.web.bookstore.bookstoreservice.model.Order_Book;
import com.web.bookstore.bookstoreservice.model.User;
import com.web.bookstore.bookstoreservice.model.User_Role;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private Order_BookRepository order_bookRepository;
	@Autowired
	private User_RoleRepository user_RoleRepository;
	
	public int order_id = 0;

	public BookController(BookRepository bookRepository, UserRepository userRepository, 
			User_RoleRepository user_RoleRepository, Order_BookRepository order_bookRepository) {
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
		this.user_RoleRepository = user_RoleRepository;
		this.order_bookRepository = order_bookRepository;
	}
	
	// GET/books
	@RequestMapping(path = "/books", method=RequestMethod.GET)
	public String retrieveAllBooks(Model model){
		
		List<Book> books =  bookRepository.findAll();
		model.addAttribute("books", books);
		return "list-books";
	}
	
	@GetMapping("/main")
	public ModelAndView retrieveAllBooksForIndex(Model model){
		
		List<Book> books =  bookRepository.findAll();
		model.addAttribute("books", books);
		ModelAndView mav = new ModelAndView("index");
		return mav;
			
	}
	
	@GetMapping("/orders")
	public String retrieveAllOrders(Model model){
			
		List<Order> orders =  orderRepository.findByDiff0();
		model.addAttribute("orders", orders);
		return "list-orders";
			
	}
	
	@PostMapping("/books/search")
    public String searchBooksByName(@Valid String name, Model model) {
        List<Book> books = bookRepository.findByNameContainingIgnoreCase(name);
        model.addAttribute("books", books);
        return "search";
    }
	
	@RequestMapping("/books/{id}/delete")
	public String deleteBook(@PathVariable("id") int id){
		bookRepository.deleteById(id);
		return "redirect:/books";
	}
	
	@GetMapping("/books/{id}")
	public EntityModel<Book> retrieveBookById(@PathVariable int id){
		Optional<Book> book = bookRepository.findById(id);
		
		EntityModel<Book> entityModel = EntityModel.of(book.get());
			
		return entityModel;
	} 
	
	@GetMapping("/add-book-form.html")
    public String showAddBookForm() {
        return "add-book-form";
    }
	
	//POST/books
	@PostMapping("/books")
	public String createBook(@Valid Book book) {
		bookRepository.save(book);
		return "redirect:/books";
	}
	
	@GetMapping("/books/{id}/update")
    public String showUpdateBookForm(@PathVariable("id") int bookId, Model model) {
        // Retrieve the book by its ID and add it to the model
        Optional<Book> optionalBook = bookRepository.findById(bookId); 
        Book book = optionalBook.get();
        model.addAttribute("book", book);
        return "update-book";
    }
	
	@PostMapping("/save")
	public String updateBook(@Valid Book updatedBook, Model model) {
		int id = updatedBook.getBookId();
	    Optional<Book> optionalBook = bookRepository.findById(id);

	    Book existingBook = optionalBook.get();
	    existingBook.setName(updatedBook.getName());
	    existingBook.setAuthor(updatedBook.getAuthor());
	    existingBook.setPublisher(updatedBook.getPublisher());
	    existingBook.setPrice(updatedBook.getPrice());	    

	    bookRepository.save(existingBook);
	    
	    List<Book> books =  bookRepository.findAll();
		model.addAttribute("books", books);
		return "redirect:/books";
	}
	
	// GET/books
		@GetMapping("/users")
		public String retrieveAllUsers(Model model){
			List<User> users =  userRepository.findAll();
			model.addAttribute("users", users);
			return "list-users";
		}
		
		@RequestMapping("/users/{id}/delete")
		public String deleteUser(@PathVariable("id") int id){
			userRepository.deleteById(id);
			return "redirect:/users";
		}
		
		@GetMapping("/users/{id}")
		public EntityModel<User> retrieveUserById(@PathVariable int id){
			Optional<User> user = userRepository.findById(id);
			
			EntityModel<User> entityModel = EntityModel.of(user.get());
			
			return entityModel;
		} 
		
		@GetMapping("/add-user-form.html")
	    public String showAddUserForm() {
	        return "add-user-form";
	    }
		
		@PostMapping("/users")
		public String createUser(@Valid User user, int role_id) {
			Optional<User_Role> optionalUser_Role = user_RoleRepository.findById(role_id);
			User_Role existingUserRole = optionalUser_Role.get();
			user.setUser_role(existingUserRole);
			
			userRepository.save(user);
			
			return "redirect:/users";
		}
		
		@GetMapping("/users/{id}/update")
	    public String showUpdateUserForm(@PathVariable("id") int userId, Model model) {
	        // Retrieve the book by its ID and add it to the model
	        Optional<User> optionalUser = userRepository.findById(userId); 
	        User user = optionalUser.get();
	        model.addAttribute("user", user);
	        return "update-user";
	    }
		
		@PostMapping("/saveuser")
		public String updateUser(@Valid User updatedUser, Model model) {
			int id = updatedUser.getUserId();
		    Optional<User> optionalUser = userRepository.findById(id);

		    User existingUser = optionalUser.get();
		    existingUser.setUsername(updatedUser.getUsername());
		    existingUser.setPassword(updatedUser.getPassword());
		    existingUser.setPay_method(updatedUser.getPay_method());

		    userRepository.save(existingUser);
		    
		    List<User> users =  userRepository.findAll();
			model.addAttribute("users", users);
		    return "redirect:/users";
		}
		
		@GetMapping("/login")
		public String directToLogin() {
			return "login";
		}
		
		@PostMapping("/accesslogin")
		public String login(@Valid User user, RedirectAttributes redirectAttributes) {
		    String username = user.getUsername();

		    // Authenticate username and password
		    User existingUser = userRepository.findByUsername(username);
		    if(existingUser == null) {
		    	redirectAttributes.addFlashAttribute("errorMessage", "Username or password is incorrect.");
		    	return "redirect:/login";
		    }
		    
		   orderRepository.deleteBy0();

		    if(existingUser.getUser_role().getRoleId() == 0) {
		    	order_id += 1;
			    
			    Order order = new Order();
			    order.setOrderId(order_id);
			    order.setUser(existingUser);
			    order.setTotal_price(0);
			    
			    orderRepository.save(order);
		    	return "redirect:/main";	
		    }
		    	return "redirect:/books";
		}
		
		@GetMapping("/register")
		public String accessToRegister() {
			return "register";
		}
		
		@PostMapping("/acessregister")
		public String register(@Valid User user, int role_id) {
			Optional<User_Role> optional_user_role = user_RoleRepository.findById(role_id);
			
			User_Role user_role = optional_user_role.get();
			user.setUser_role(user_role);
		    userRepository.save(user);

		    // Redirect to the login page
		    return "redirect:/login";
		}
		
		@RequestMapping("/orderbook/{id}")
	    public String buyProduct(@PathVariable("id") int bookId, @RequestParam("quantity") int quantity, HttpSession session) {
	    
		    Order_Book orderBook = new Order_Book();
		    orderBook.setQuantity(quantity);
		    
		    Optional<Book> optionalBook = bookRepository.findById(bookId);
		    Book book = optionalBook.orElseThrow(() -> new IllegalArgumentException("Book not found"));

		    Order order = orderRepository.findBy0();

		    if (book.getOrder_books() == null) {
		        book.setOrder_books(new ArrayList<>());
		    }

		    // Associate the book with the order_book
		    orderBook.setBook(book);

		    // Associate the order with the order_book
		    orderBook.setOrder(order);

		    // Add the order_book to the book
		    book.getOrder_books().add(orderBook);

		    if (order.getOrder_books() == null) {
		        order.setOrder_books(new ArrayList<>());
		    }

		    // Add the order_book to the order
		    order.getOrder_books().add(orderBook);

		    // Save the book to update the order_book association
		    bookRepository.save(book);

		    // Save the order to update the order_book association
		    orderRepository.save(order);

		    // Save the order_book
		    order_bookRepository.save(orderBook);

		    return "redirect:/main";
	    }
		
		 @GetMapping("/confirm/{id}")
		    public String Confirm(@PathVariable("id") int userId, HttpSession session, Model model) {
			 order_id += 1;
			 
			 Optional<User> optionalUser = userRepository.findById(userId);
			 User existingUser = optionalUser.get();
			    
			 Order order = new Order();
			 order.setOrderId(order_id);
			 order.setUser(existingUser);
			 order.setTotal_price(0);
			    
			 orderRepository.save(order);

		     return "success";
		    }
}
