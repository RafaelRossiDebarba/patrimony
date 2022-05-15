package br.upf.patrimony.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.upf.patrimony.entities.Departament;
import br.upf.patrimony.exceptions.DatabaseException;
import br.upf.patrimony.exceptions.ResourceNotFoundException;
import br.upf.patrimony.repositories.DepartamentRepository;

@Service
public class DepartamentService {
	
	@Autowired
	private DepartamentRepository repository;
	
	public List<Departament> findAll() {
		return repository.findAll();
	}
	
	public Departament newRegister(Departament departament) {
		return repository.save(departament);
	}
	
	public Departament findById(Long id) {
		return repository.findById(id).get();
	}
	
	public void deleteDepartament(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(
					"Departamento não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(
					"Departamento com vínculos existente"
					);
		}
	}
	
	public Departament editProduct
		(Long id, Departament departament) {
		Departament departamentDB = findById(id);
		
		BeanUtils.copyProperties(departament, departamentDB, "id");
		
		return repository.save(departamentDB);
	}

}
