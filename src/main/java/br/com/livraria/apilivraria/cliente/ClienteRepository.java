package br.com.livraria.apilivraria.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("FROM Cliente l " +  "WHERE LOWER(l.nome) like %:searchTerm%")
	Page<Cliente> findAll(@Param("searchTerm") String searchTerm, Pageable pageable);

}
