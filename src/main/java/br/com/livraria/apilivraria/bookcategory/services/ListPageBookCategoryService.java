package br.com.livraria.apilivraria.bookcategory.services;

import org.springframework.data.domain.Page;

import br.com.livraria.apilivraria.bookcategory.BookCategory;

@FunctionalInterface
public interface ListPageBookCategoryService {

	Page<BookCategory> findPage(String nome, int page, int size);


}
