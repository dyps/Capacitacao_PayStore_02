package br.com.livraria.apilivraria.livro.services;

import br.com.livraria.apilivraria.livro.Livro;

@FunctionalInterface
public interface UpdateLivroService {

	void update(Livro newLivro, Long id);

}
