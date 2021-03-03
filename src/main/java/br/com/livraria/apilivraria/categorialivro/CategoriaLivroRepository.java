package br.com.livraria.apilivraria.categorialivro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaLivroRepository extends JpaRepository<CategoriaLivro, Long> {


}
