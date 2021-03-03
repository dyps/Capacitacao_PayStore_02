package br.com.livraria.apilivraria.categorialivro.services;

import br.com.livraria.apilivraria.categorialivro.CategoriaLivro;

@FunctionalInterface
public interface GetCategoriaLivroService {

	CategoriaLivro find(Long id);

}

