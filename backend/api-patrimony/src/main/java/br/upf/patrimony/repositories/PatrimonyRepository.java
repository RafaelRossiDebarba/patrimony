package br.upf.patrimony.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upf.patrimony.entities.Patrimony;


@Repository
public interface PatrimonyRepository extends JpaRepository<Patrimony, Long>{
	
	Page<Patrimony> findByNameContaining(
			String name, Pageable pageable
			);
}
