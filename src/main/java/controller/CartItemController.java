package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.CartItemService;

@RestController
@RequestMapping("/items")
public class CartItemController {
	private CartItemService service;
    
	@Autowired
	public CartItemController(CartItemService service) {
		this.service = service;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteItem(@PathVariable int id){
		this.service.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
