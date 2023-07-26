package controller;

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
import entity.User;
import service.AddressService;
import service.UserService;

@RestController
@RequestMapping("/addresses")
public class AddressController {
	private AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService){
		super();
		this.addressService = addressService;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Address> getById(@PathVariable int id){
		Address address = this.addressService.findById(id);
		return new ResponseEntity<Address>(address,HttpStatus.OK);
	}
	
	@PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address){	
		Address newAddress = this.addressService.create(address);
		return new ResponseEntity<Address>(newAddress,HttpStatus.OK);
	}	
	
	@PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable int id,@RequestBody Address address){	
		address.setId(id);
		Address newAddress = this.addressService.update(address);
		return new ResponseEntity<Address>(newAddress,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteAddress(@PathVariable int id){
	   this.addressService.delete(id);
	   return new ResponseEntity(HttpStatus.OK);
	};
}
