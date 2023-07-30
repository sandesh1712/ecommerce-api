package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DTO.CartDTO;
import entity.Cart;
import entity.CartItem;
import service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    
	
	public CartController(CartService cartService){
		super();
		this.cartService = cartService;
	}

	@PostMapping("/{id}/additem")
	public ResponseEntity<CartDTO> addToCart(@PathVariable int id, @RequestBody CartItem cartItem){
		Cart cart = this.cartService.addToCart(id, cartItem);        		
		return new ResponseEntity<CartDTO>(cart.convertToDto(),HttpStatus.OK);
	}
	
	@PutMapping("/{id}/removeitem")
	public ResponseEntity<CartDTO> removeFromCart(@PathVariable int id, @RequestBody CartItem cartItem){
		Cart cart= this.cartService.removeFromCart(id, cartItem);
		return new ResponseEntity<CartDTO>(cart.convertToDto(),HttpStatus.OK);
	}
}
