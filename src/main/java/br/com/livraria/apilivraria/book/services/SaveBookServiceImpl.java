package br.com.livraria.apilivraria.book.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.book.Book;
import br.com.livraria.apilivraria.book.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveBookServiceImpl implements SaveBookService {

    private final BookRepository bookRepository;

    @Override
    public void insert(Book book) {
        bookRepository.save(book);
    }
}
