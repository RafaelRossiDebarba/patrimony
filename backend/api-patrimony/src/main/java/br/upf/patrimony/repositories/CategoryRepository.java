package br.upf.patrimony.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upf.patrimony.entities.Category;

@Repository
public interface CategoryRepository
		extends JpaRepository<Category, Long>{

}
