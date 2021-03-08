package br.com.livraria.apilivraria.book.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.book.Book;
import br.com.livraria.apilivraria.book.BookRepository;
import br.com.livraria.apilivraria.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetBookServiceImpl implements GetBookService {

    private final BookRepository bookRepository;

    @Override
    public Book find(Long id) {
		return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

}
