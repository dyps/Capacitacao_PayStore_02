package br.com.livraria.apilivraria.book.services;

import br.com.livraria.apilivraria.book.Book;

@FunctionalInterface
public interface GetBookService {

	Book find(Long id);

}
