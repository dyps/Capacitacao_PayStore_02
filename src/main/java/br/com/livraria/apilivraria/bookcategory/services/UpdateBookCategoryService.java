package br.com.livraria.apilivraria.bookcategory.services;

import br.com.livraria.apilivraria.bookcategory.BookCategory;

@FunctionalInterface
public interface UpdateBookCategoryService {

	void update(BookCategory cliente, Long id);

}