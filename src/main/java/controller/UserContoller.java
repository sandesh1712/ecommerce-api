package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.Address;
import entity.Review;
import entity.User;
import service.UserService;

@RestController
@RequestMapping("/users")
public class UserContoller {
	private UserService userService;

	@Autowired
	public UserContoller(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> userList = this.userService.findAll();
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id){
	   User user = this.userService.findById(id);
	   return new ResponseEntity<User>(user,HttpStatus.OK);
	};
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User userBody){
	   User user = this.userService.create(userBody);
	   return new ResponseEntity<User>(user,HttpStatus.CREATED);
	};
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User userBody){
		userBody.setId(id);
		User user = this.userService.create(userBody);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	};
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable int id){
	   this.userService.delete(id);
	   return new ResponseEntity<User>(HttpStatus.OK);
	};
	
	@GetMapping("/{userId}/addresses")
	public ResponseEntity<List<Address>> getAllAddressesByUserId(@PathVariable int userId){
		User user = this.userService.findById(userId);
		List<Address> list = user.getAddresses();
		return new ResponseEntity<List<Address>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/{userId}/reviews")
	public ResponseEntity<List<Review>> getAllReviewsByUserId(@PathVariable int userId){
		User user = this.userService.findById(userId);
		List<Review> list = user.getReviews();
		return new ResponseEntity<List<Review>>(list,HttpStatus.OK);
	}
}