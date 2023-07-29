package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.User;
import exceptions.NotAllowedException;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import repository.UserRepository;


@Service
@Transactional
public class UserService implements ServiceInterface<User>{
	private UserRepository repo;
	CartService cartService;
	
    
	@Autowired
	public UserService(UserRepository repo){
		super();
		this.repo = repo;
		this.cartService = cartService;
	}

	@Override
	public User findById(int id) {
		Optional<User> user = this.repo.findById(id);
      //throw exception if user not found		
		if(user.isEmpty())
			throw new NotFoundException("User Not Found");
		return user.get();
	}

	@Override
	public List<User> findAll() {
		return this.repo.findAll();
	}

	@Override
	public User create(User u) {
		if(u.getId()!=null) {
			throw new NotAllowedException("Id shouldn't be passed");
		}
		User newUser = this.repo.save(u);
	//	cartService.createCartForUser(newUser);
		return newUser;	
	}

	@Override
	public User update(User u) {
		User user = this.findById(u.getId());
		return this.repo.save(u);
	}

	@Override
	public void delete(int id) {
		User user = this.findById(id);
		this.repo.delete(user);
	}
	
	public User findByEmail(String email) {
		Optional<User> user = this.repo.findByEmail(email);
		if(user.isEmpty())
			throw new NotFoundException("User Not Found");
		return user.get();
	}
}
