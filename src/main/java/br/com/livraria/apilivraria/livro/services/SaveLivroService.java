package br.com.livraria.apilivraria.livro.services;

import br.com.livraria.apilivraria.livro.Livro;

@FunctionalInterface
public interface SaveLivroService {

	void insert(Livro livro);

}
