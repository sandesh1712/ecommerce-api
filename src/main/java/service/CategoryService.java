package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Category;
import exceptions.NotAllowedException;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import repository.CategoryRepository;

@Service
@Transactional
public class CategoryService implements ServiceInterface<Category> {
	private CategoryRepository repo;

	@Autowired
	public CategoryService(CategoryRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public List<Category> findAll(){
		return this.repo.findAll();
	}
	
	@Override
	public Category findById(int id) {
		Optional<Category> category = this.repo.findById(id);
		if(category.isEmpty())
			throw new NotFoundException("Category Not Found");
		return category.get();
	}
	
	@Override
	public Category create(Category category) {
	   if(category.getId()!=null)
		   throw new NotAllowedException("Id shouldn't be passed!");
	   return this.repo.save(category);
	}
	
	@Override
	public Category update(Category category) {
		findById(category.getId());
		return this.repo.save(category);
	}
	
	@Override
	public void delete(int id) {
		Category category = this.findById(id);
		this.repo.delete(category);
	}	
}
