package br.com.livraria.apilivraria.book.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.book.Book;
import br.com.livraria.apilivraria.book.BookRepository;
import br.com.livraria.apilivraria.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdateBookServiceImpl implements UpdateBookService {

	private final BookRepository bookRepository;

	@Override
	public void update(Book newBook, Long id) {
		Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
		book.setTitle(newBook.getTitle());
		book.setSynopsis(newBook.getSynopsis());
		book.setIsbn(newBook.getIsbn());
		book.setAuthor(newBook.getAuthor());
		book.setYearPublication(newBook.getYearPublication());
		book.setPriceSale(newBook.getPriceSale());
		book.setAvailableQuantity(newBook.getAvailableQuantity());
		book.setBookCategories(newBook.getBookCategories());
		bookRepository.save(book);
	}
}
