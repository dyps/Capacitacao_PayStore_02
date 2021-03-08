package br.com.livraria.apilivraria.bookcategory.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.bookcategory.BookCategoryRepository;
import br.com.livraria.apilivraria.exceptions.BookCategoryNotDeletedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteBookCategoryServiceImpl implements DeleteBookCategoryService {

	private final BookCategoryRepository categoriaLivroRepository;

	@Override
	public void delete(Long id) {
		if (!categoriaLivroRepository.existsById(id)) {
			throw new BookCategoryNotDeletedException();
		}
		categoriaLivroRepository.deleteById(id);
	}
}
