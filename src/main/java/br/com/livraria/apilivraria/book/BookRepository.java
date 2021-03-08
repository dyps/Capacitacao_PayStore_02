package br.com.livraria.apilivraria.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("FROM Book l " +  "WHERE LOWER(l.title) like %:searchTerm%")
	Page<Book> findAll(@Param("searchTerm") String searchTerm, Pageable pageable);
	
}
