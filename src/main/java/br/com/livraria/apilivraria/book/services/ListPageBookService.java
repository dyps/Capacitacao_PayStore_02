package br.com.livraria.apilivraria.book.services;

import org.springframework.data.domain.Page;

import br.com.livraria.apilivraria.book.Book;

@FunctionalInterface
public interface ListPageBookService {

	Page<Book> findPage(String searchTerm, Integer page, Integer size);

}
