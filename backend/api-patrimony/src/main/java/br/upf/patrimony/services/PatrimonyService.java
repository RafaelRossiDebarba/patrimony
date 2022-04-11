package br.upf.patrimony.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.upf.patrimony.dto.PatrimonyDTO;
import br.upf.patrimony.entities.Patrimony;
import br.upf.patrimony.exceptions.DatabaseExcetion;
import br.upf.patrimony.exceptions.ResourceNotFoundException;
import br.upf.patrimony.repositories.PatrimonyRepository;

@Service
public class PatrimonyService {
	
	@Autowired
	private PatrimonyRepository repository;
	
	public List<Patrimony> findAll() {
		return repository.findAll();
	}
	
	public Patrimony findById(Long id) {
		Optional<Patrimony> opt = repository.findById(id);
		
		Patrimony patrimony = opt.orElseThrow(
				() -> new ResourceNotFoundException("Patrimonio não encontrado")
				);
		
		return patrimony;
	}
	
	public Patrimony newRegister(Patrimony patrimony) {
		Patrimony patrimonySave = repository.save(patrimony);
		return patrimonySave;
	}
	
	public Patrimony editPatrimony(Long id, Patrimony patrimony) {
		Patrimony patrimonyDb = findById(id);
		
		BeanUtils.copyProperties(patrimony, patrimonyDb, "id", "code", "audit");
		
		return repository.save(patrimonyDb);
	}
	
	public void deletePatrimony(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Patrimônio não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseExcetion("Patrimônio com vínculo existente");
		}
	}

	public Page<PatrimonyDTO> findAllPaged(PageRequest pageRequest, String name) {
		Page<Patrimony> list;
		
		if(name == null) {
			list = repository.findAll(pageRequest);
		}
		else {
			list = repository.findByNameContaining(name, pageRequest);
		}
		
		return list.map(
				patrimony -> new PatrimonyDTO(patrimony)
			);
	}
}
