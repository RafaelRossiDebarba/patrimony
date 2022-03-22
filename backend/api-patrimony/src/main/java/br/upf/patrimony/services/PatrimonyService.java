package br.upf.patrimony.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upf.patrimony.entities.Patrimony;
import br.upf.patrimony.repositories.PatrimonyRepository;

@Service
public class PatrimonyService {
	
	@Autowired
	private PatrimonyRepository repository;
	
	public List<Patrimony> findAll() {
		return repository.findAll();
	}
	
	public Patrimony findById(Long id) {
		return repository.findById(id).get();
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
		repository.deleteById(id);
	}
}
