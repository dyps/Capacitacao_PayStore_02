package br.com.livraria.apilivraria.categorialivro.services;

import br.com.livraria.apilivraria.categorialivro.CategoriaLivro;

@FunctionalInterface
public interface SaveCategoriaLivroService {

	void insert(CategoriaLivro cliente);

}