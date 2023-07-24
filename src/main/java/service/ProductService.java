package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import entity.Product;
import exceptions.NotFoundException;
import repository.ProductRepository;

public class ProductService implements ServiceInterface<Product> {
	private ProductRepository repo;

	@Autowired
	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}

	@Override
	public Product findById(int id) {
		 Optional<Product> product = this.repo.findById(id);  
		 if(product.isEmpty())
			 throw new NotFoundException("Product Not Found!!");
		 return product.get();
	}

	@Override
	public List<Product> findAll() {
		return this.repo.findAll();
	}	

	@Override
	public Product create(Product t) {	
		return null;
	}

	@Override
	public Product update(Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	

}
