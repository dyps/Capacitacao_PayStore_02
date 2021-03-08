package br.com.livraria.apilivraria.book.services;

import java.util.List;

import br.com.livraria.apilivraria.book.Book;

@FunctionalInterface
public interface ListBookService {

	List<Book> findAll();

}
