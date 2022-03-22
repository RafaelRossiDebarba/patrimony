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

import br.upf.patrimony.entities.Patrimony;
import br.upf.patrimony.services.PatrimonyService;

@RestController
@RequestMapping("/patrimony")
public class PatrimonyResource {
	
	@Autowired
	private PatrimonyService service;
	
	@GetMapping("/all")
	public List<Patrimony> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/byid/{id}")
	public Patrimony findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Patrimony newPatrimony(@RequestBody Patrimony patrimony) {
		return service.newRegister(patrimony);
	}
	
	@PutMapping("/{id}")
	public Patrimony editPatrimony(@PathVariable Long id, @RequestBody Patrimony patrimony) {
		return service.editPatrimony(id, patrimony);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePatrimony(@PathVariable Long id) {
		service.deletePatrimony(id);
	}
}
