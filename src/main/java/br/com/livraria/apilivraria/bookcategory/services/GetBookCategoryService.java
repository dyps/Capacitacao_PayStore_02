package br.com.livraria.apilivraria.bookcategory.services;

import br.com.livraria.apilivraria.bookcategory.BookCategory;

@FunctionalInterface
public interface GetBookCategoryService {

	BookCategory find(Long id);

}

