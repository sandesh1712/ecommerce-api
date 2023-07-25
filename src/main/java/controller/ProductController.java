package controller;

import java.util.List;

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

import entity.Product;
import entity.ProductImages;
import entity.ProductInventory;
import entity.Review;
import exceptions.NotAllowedException;
import service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> list = this.productService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) {
		Product product = this.productService.findById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		List<ProductImages> productImagesList = null;
		List<ProductInventory> productInventoryList = null;

		if (product.getProductImages() != null) {
			productImagesList = product.getProductImages();
			product.setProductImages(null);
		}

		if (product.getProductInventories() != null) {
			productInventoryList = product.getProductInventories();
			product.setProductInventories(null);
		}

		Product newProduct = this.productService.create(product);

		if (productImagesList != null) {
			productImagesList.forEach(image -> {
				image.setProduct(newProduct);
			});
			newProduct.setProductImages(productImagesList);
		}

		if (productInventoryList != null) {
			productInventoryList.forEach(inventory -> {
				inventory.setProduct(newProduct);
			});
			newProduct.setProductInventories(productInventoryList);
		}
		Product updatedProduct = this.productService.update(newProduct);
		return new ResponseEntity<Product>(this.productService.findById(updatedProduct.getId()), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
		if (product.getId() != null)
			throw new NotAllowedException("Passing Id in body Not allowed!");
		product.setId(id);
		Product updatedProduct = this.productService.update(product);
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteProduct(@PathVariable int id) {
		this.productService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/{id}/reviews")
	public ResponseEntity<List<Review>> getProductReviews(@PathVariable int id) {
		Product product = this.productService.findById(id);
		List<Review> list = product.getProductReviews();
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}
}
