package br.upf.patrimony.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upf.patrimony.entities.Departament;

@Repository
public interface DepartamentRepository
		extends JpaRepository<Departament, Long>{

}
