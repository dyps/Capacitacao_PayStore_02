package br.com.livraria.apilivraria.bookcategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
	
	

	@Query("FROM BookCategory l " +  "WHERE LOWER(l.name) like %:searchTerm%")
	Page<BookCategory> findAll(@Param("searchTerm") String searchTerm, Pageable pageable);



}
