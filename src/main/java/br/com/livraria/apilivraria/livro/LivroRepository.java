package br.com.livraria.apilivraria.livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

	@Query("FROM Livro l " +  "WHERE LOWER(l.titulo) like %:searchTerm%")
	Page<Livro> findAll(@Param("searchTerm") String searchTerm, Pageable pageable);
	
}
