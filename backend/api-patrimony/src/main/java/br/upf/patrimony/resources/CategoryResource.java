package br.upf.patrimony.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.upf.patrimony.entities.Category;
import br.upf.patrimony.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;

	@GetMapping("/all")
	public List<Category> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/byid/{id}")
	public Category findByid(
			@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Category newCategory(
			@RequestBody Category category
			) {
		return service.newRegister(category);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCategory(
			@PathVariable Long id) {
		service.deleteCategory(id);
	}
	
	@PutMapping("/{id}")
	public Category editCategory(
			@PathVariable Long id, 
			@RequestBody Category category
			) {
		return service.editCategory(id, category);
	}
}
