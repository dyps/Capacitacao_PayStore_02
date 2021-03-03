package br.com.livraria.apilivraria.categorialivro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaLivroRepository extends JpaRepository<CategoriaLivro, Long> {
	
	

	@Query("FROM CategoriaLivro l " +  "WHERE LOWER(l.nome) like %:searchTerm%")
	Page<CategoriaLivro> findAll(@Param("searchTerm") String searchTerm, Pageable pageable);



}
