package br.com.livraria.apilivraria.book.services;

import br.com.livraria.apilivraria.book.Book;

@FunctionalInterface
public interface UpdateBookService {

	void update(Book newBook, Long id);

}
