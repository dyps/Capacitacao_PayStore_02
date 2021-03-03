package br.com.livraria.apilivraria.categorialivro.services;

import br.com.livraria.apilivraria.categorialivro.CategoriaLivro;

@FunctionalInterface
public interface UpdateCategoriaLivroService {

	void update(CategoriaLivro cliente, Long id);

}