package service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.CartItem;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import repository.CartItemRepository;

@Service
@Transactional
public class CartItemService implements ServiceInterface<CartItem>{

	private CartItemRepository repository;	
	
	@Autowired
	public CartItemService(CartItemRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public CartItem findById(int id) {
		Optional<CartItem> item = this.repository.findById(id);
		if(item.isEmpty()) 
			throw new NotFoundException("Item Not found!!");
		return item.get();
	}

	@Override
	public List<CartItem> findAll() {
		return null;
	}

	@Override
	public CartItem create(CartItem t) {
		
		return null;
	}

	@Override
	public CartItem update(CartItem t) {
		this.findById(t.getId());
		return this.repository.save(t);
	}

	@Override
	public void delete(int id) {
      CartItem item = this.findById(id);
      this.repository.delete(item);
	}

}
