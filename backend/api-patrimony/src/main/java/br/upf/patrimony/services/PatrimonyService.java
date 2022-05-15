package br.upf.patrimony.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.upf.patrimony.dto.PatrimonyDTO;
import br.upf.patrimony.entities.Category;
import br.upf.patrimony.entities.Patrimony;
import br.upf.patrimony.exceptions.DatabaseException;
import br.upf.patrimony.exceptions.ResourceNotFoundException;
import br.upf.patrimony.repositories.CategoryRepository;
import br.upf.patrimony.repositories.PatrimonyRepository;

@Service
public class PatrimonyService {
	
	@Autowired
	private PatrimonyRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Patrimony> findAll() {
		return repository.findAll();
	}
	
	public Patrimony newRegister(Patrimony patrimony) {
		return repository.save(patrimony);
	}
	
	public Patrimony findById(Long id) {
		Optional<Patrimony> opt =
				repository.findById(id);
		
		Patrimony patrimony =
				opt.orElseThrow(
   () -> new ResourceNotFoundException(
		 "Patrimônio não encontrado"
		   )
						);
		
		return patrimony;
	}
	
	public void deletePatrimony(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(
					"Patrimônio não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(
					"Patrimônio com vínculos existente"
					);
		}
	}
	
	public Patrimony editPatrimony
		(Long id, Patrimony patrimony) {
		Patrimony patrimonyDB = findById(id);

		patrimonyDB.setStatus(patrimony.getStatus());
		patrimonyDB.setName(patrimony.getName());
		patrimonyDB.setDescription(patrimony.getDescription());
		patrimonyDB.setImgUrl(patrimony.getImgUrl());
		patrimonyDB.setPrice(patrimony.getPrice());
		patrimonyDB.setDepartament(patrimony.getDepartament());
		patrimonyDB.setCode(patrimony.getCode());
		
		patrimonyDB.getCategories().clear();
		for (Category cat : patrimony.getCategories()) {
			Category catDB = categoryRepository.findById(cat.getId()).get();
			patrimonyDB.getCategories().add(catDB);
		}
		
		return repository.save(patrimonyDB);
	}

	public Page<PatrimonyDTO> findAllPaged(
			PageRequest pageRequest,
			String name) {
		Page<Patrimony> list;
		
		if (name == null) {
		  list = repository.findAll(pageRequest);
		} else {
			list = repository.findByNameContaining(
					name, pageRequest);
		}
		
		return list.map(
			patrimony -> new PatrimonyDTO(patrimony)	
				);
	}

}
