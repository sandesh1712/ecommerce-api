package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Product;
import entity.ProductImages;
import entity.ProductInventory;
import exceptions.NotAllowedException;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import repository.ProductRepository;

@Service
@Transactional
public class ProductService implements ServiceInterface<Product> {
	private ProductRepository repo;

	@Autowired
	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}

	@Override
	public Product findById(int id) {
		Optional<Product> product = this.repo.findById(id);
		if (product.isEmpty())
			throw new NotFoundException("Product Not Found!!");
		return product.get();
	}

	@Override
	public List<Product> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Product create(Product product) {
		if (product.getId() != null)
			throw new NotAllowedException("Id shouldn't be passed!");
		return this.repo.save(product);
	}

	@Override
	public Product update(Product product) {
		findById(product.getId());
		return this.repo.save(product);
	}

	@Override
	public void delete(int id) {
		Product product = this.findById(id);
		this.repo.delete(product);
	}
}
