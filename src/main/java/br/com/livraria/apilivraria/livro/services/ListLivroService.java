package br.com.livraria.apilivraria.livro.services;

import java.util.List;

import br.com.livraria.apilivraria.livro.Livro;

@FunctionalInterface
public interface ListLivroService {

	List<Livro> findAll();

}
