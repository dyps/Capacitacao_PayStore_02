package br.com.livraria.apilivraria.book.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.book.Book;
import br.com.livraria.apilivraria.book.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListBookServiceImpl implements ListBookService {

	private final BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

}
