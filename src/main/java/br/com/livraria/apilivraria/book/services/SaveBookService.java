package br.com.livraria.apilivraria.book.services;

import br.com.livraria.apilivraria.book.Book;

@FunctionalInterface
public interface SaveBookService {

	void insert(Book book);

}
