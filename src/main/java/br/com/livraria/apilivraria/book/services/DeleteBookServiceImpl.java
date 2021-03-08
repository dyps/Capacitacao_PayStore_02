package br.com.livraria.apilivraria.book.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.exceptions.BookNotDeletedException;
import br.com.livraria.apilivraria.book.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteBookServiceImpl implements DeleteBookService {

	private final BookRepository bookRepository;

	@Override
	public void delete(Long id) {
		if (!bookRepository.existsById(id)) {
			throw new BookNotDeletedException();
		}
		bookRepository.deleteById(id);
	}
}
