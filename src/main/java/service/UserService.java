package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.User;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import repository.UserRepository;


@Service
@Transactional
public class UserService implements ServiceInterface<User>{
	private UserRepository repo;
    
	@Autowired
	public UserService(UserRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		Optional<User> user = this.repo.findById(id);
      //throw exception if user not found		
		if(user.isEmpty())
			throw new NotFoundException("User Not Found");
		return user.get();
	}

	@Override
	public List<User> findALL() {
		return this.repo.findAll();
	}

	@Override
	public User create(User u) {
		return this.repo.save(u);
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
}
