package br.com.livraria.apilivraria.bookcategory.services;

import java.util.List;

import br.com.livraria.apilivraria.bookcategory.BookCategory;

@FunctionalInterface
public interface ListBookCategoryService {

	List<BookCategory> findAll();

}
