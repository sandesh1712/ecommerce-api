package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Cart;
import entity.CartItem;
import entity.Product;
import entity.User;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import repository.CartRepository;
import types.CartStatus;

@Service
@Transactional
public class CartService implements ServiceInterface<Cart> {
	private CartRepository repo;

	@Autowired
	public CartService(CartRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Cart findById(int id) {
		Optional<Cart> cart = this.repo.findById(id);
		if (cart.isEmpty())
			throw new NotFoundException("Cart Not Cound!!");
		return cart.get();
	}

	@Override
	public List<Cart> findAll() {
		return this.findAll();
	}

	@Override
	public Cart create(Cart cart) {
		return this.repo.save(cart);
	}

	@Override
	public Cart update(Cart cart) {
		findById(cart.getId());
		return this.repo.save(cart);
	}

	@Override
	public void delete(int id) {
		// implement later
	}

	public Cart createCartForUser(User newUser) {
		Cart cart = new Cart();
		cart.setUser(newUser);
		cart.setStatus(CartStatus.ACTIVE);
		return this.create(cart);
	}
	
	public Cart addToCart(int id,CartItem item) {
		Cart cart  = this.findById(id);
		if(cart.getItems()==null)
			cart.setItems(new ArrayList<>());
		cart.getItems().add(item);	
		cart.setCartTotal(calculateTotal(cart));
		return this.repo.save(cart);
	}
	
	public float calculateTotal(Cart cart){
		float total=0;
		List<CartItem> items = cart.getItems();
		for(CartItem item : items) {
			total += item.getTotal();
		}
		return total;
	}
	
	public Cart removeFromCart(int id,CartItem item){
		Cart cart  = this.findById(id);
		cart.getItems().remove(item);
		cart.setCartTotal(calculateTotal(cart));
		return this.repo.save(cart);
	}
}
