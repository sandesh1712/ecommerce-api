package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Address;
import exceptions.NotFoundException;
import exceptions.UnauthorisedException;
import jakarta.transaction.Transactional;
import repository.AddressRepository;

@Service
@Transactional
public class AddressService implements ServiceInterface<Address> {
	private AddressRepository repo;

	@Autowired
	public AddressService(AddressRepository repo) {
		this.repo = repo;
	}

	public List<Address> getAllForUser(int user_id) {
       return this.repo.findAllByUserId(user_id);
	}

	@Override
	public Address findById(int id) {
		Optional<Address> address = this.repo.findById(id);
		if(address.isEmpty())
			throw new NotFoundException("Address Not Found!");
		return address.get();
	}

	@Override
	public List<Address> findALL() {
		return this.repo.findAll();
	}

	@Override
	public Address create(Address address) {
		if(address.getId()!=null) {
			throw new RuntimeException("Id shouldn't be passed");
		}
		return this.repo.save(address);
	}

	@Override
	public Address update(Address address) {
		Address address2 = this.findById(address.getId());
		if(address.getUser().getId()!=address2.getUser().getId())
			throw new UnauthorisedException("Unauthorised: Modifying user id not allowed!!");
		return this.repo.save(address);
	}

	@Override
	public void delete(int id) {
        Address address = this.findById(id);
        this.repo.delete(address);
	}
}
