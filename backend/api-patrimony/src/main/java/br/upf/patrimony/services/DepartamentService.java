package br.upf.patrimony.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.upf.patrimony.entities.Departament;
import br.upf.patrimony.exceptions.DatabaseExcetion;
import br.upf.patrimony.exceptions.ResourceNotFoundException;
import br.upf.patrimony.repositories.DepartamentRepository;

@Service
public class DepartamentService {
	
	@Autowired
	private DepartamentRepository repository;
	
	public List<Departament> findAll() {
		return repository.findAll();
	}
	
	public Departament findById(Long id) {
		return repository.findById(id).get();
	}
	
	public Departament newRegister(Departament departament) {
		Departament departamentSave = repository.save(departament);
		return departamentSave;
	}
	
	public Departament editDepartament(Long id, Departament departament) {
		Departament departamentDb = findById(id);
		
		BeanUtils.copyProperties(departament, departamentDb, "id");
		
		return repository.save(departamentDb);
	}
	
	public void deleteCategory(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Deartamento não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseExcetion("Deartamento com vínculo existente");
		}
	}
}
