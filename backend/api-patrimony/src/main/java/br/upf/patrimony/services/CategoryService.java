package br.upf.patrimony.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upf.patrimony.entities.Category;
import br.upf.patrimony.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		return repository.findById(id).get();
	}
	
	public Category newRegister(Category category) {
		Category categorySave = repository.save(category);
		return categorySave;
	}
	
	public Category editCategory(Long id, Category category) {
		Category categoryDb = findById(id);
		
		BeanUtils.copyProperties(category, categoryDb, "id");
		
		return repository.save(categoryDb);
	}
	
	public void deleteCategory(Long id) {
		repository.deleteById(id);
	}
}
