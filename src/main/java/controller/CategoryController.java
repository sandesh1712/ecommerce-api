package controller;

import java.util.List;

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

import entity.Category;
import entity.Product;
import exceptions.NotAllowedException;
import service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
   
	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> list = this.categoryService.findAll();
		return new ResponseEntity<List<Category>>(list,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
		Category newCategory = this.categoryService.create(category);
		return new ResponseEntity<Category>(newCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable int id,@RequestBody Category category){
		if(category.getId()!=null)
		    throw new NotAllowedException("Not Allowed!");
		 
		category.setId(id);    
		Category newCategory = this.categoryService.update(category);
		return new ResponseEntity<Category>(newCategory,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable int id){
		Category category = this.categoryService.findById(id);
		return new ResponseEntity<Category>(category,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteCategory(@PathVariable int id){
		this.categoryService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}/products")
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable int categoryId){
		Category category = this.categoryService.findById(categoryId);
		List<Product> list = category.getProducts();
		return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
	}
}
