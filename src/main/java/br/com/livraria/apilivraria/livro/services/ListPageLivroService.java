package br.com.livraria.apilivraria.livro.services;

import org.springframework.data.domain.Page;

import br.com.livraria.apilivraria.livro.Livro;

@FunctionalInterface
public interface ListPageLivroService {

	Page<Livro> findPage(String searchTerm, Integer page, Integer size);

}
