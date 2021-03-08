package br.com.livraria.apilivraria.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query("FROM Client l " +  "WHERE LOWER(l.name) like %:searchTerm%")
	Page<Client> findAll(@Param("searchTerm") String searchTerm, Pageable pageable);

}
