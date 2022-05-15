package br.upf.patrimony.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.upf.patrimony.dto.PatrimonyDTO;
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
	public Patrimony findByid(
			@PathVariable Long id) {
		return service.findById(id);
	}
	
	@GetMapping("/filtered")
	public ResponseEntity<Page<PatrimonyDTO>> findAllPaged(
	@RequestParam(value="page", 
					defaultValue = "0") Integer page,
	@RequestParam(value = "linesPerPage",
					defaultValue = "5") Integer linesPerPage,
	@RequestParam(value = "orderBy",
					defaultValue = "name") String orderBy,
	@RequestParam(value = "direction",
					defaultValue = "ASC") String direction,
	@RequestParam(required = false) String name
			) {
		PageRequest pageRequest = PageRequest.of(
					page, linesPerPage, 
					Direction.valueOf(direction), 
					orderBy
				);
		
		Page<PatrimonyDTO> list = 
					service.findAllPaged(pageRequest, name);
		
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Patrimony newPatrimony(
			@RequestBody Patrimony patrimony
			) {
		return service.newRegister(patrimony);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePatrimony(
			@PathVariable Long id) {
		service.deletePatrimony(id);
	}
	
	@PutMapping("/{id}")
	public Patrimony editPatrimony(
			@PathVariable Long id, 
			@RequestBody Patrimony patrimony
			) {
		return service.editPatrimony(id, patrimony);
	}
}
