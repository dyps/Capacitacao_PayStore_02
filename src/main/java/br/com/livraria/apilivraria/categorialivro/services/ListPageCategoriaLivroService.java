package br.com.livraria.apilivraria.categorialivro.services;

import org.springframework.data.domain.Page;

import br.com.livraria.apilivraria.categorialivro.CategoriaLivro;

@FunctionalInterface
public interface ListPageCategoriaLivroService {

	Page<CategoriaLivro> findPage(String nome, int page, int size);


}
