package br.com.livraria.apilivraria.compra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

	@Query("FROM Compra l " + "WHERE l.cliente = :searchTerm")
	Page<Compra> findAll(@Param("searchTerm") Integer searchTerm, Pageable pageable);

}
