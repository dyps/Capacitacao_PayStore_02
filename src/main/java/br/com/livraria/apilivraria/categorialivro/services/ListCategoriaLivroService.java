package br.com.livraria.apilivraria.categorialivro.services;

import java.util.List;

import br.com.livraria.apilivraria.categorialivro.CategoriaLivro;

@FunctionalInterface
public interface ListCategoriaLivroService {

	List<CategoriaLivro> findAll();

}
