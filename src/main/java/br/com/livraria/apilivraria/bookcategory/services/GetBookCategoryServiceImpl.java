package br.com.livraria.apilivraria.bookcategory.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.bookcategory.BookCategory;
import br.com.livraria.apilivraria.bookcategory.BookCategoryRepository;
import br.com.livraria.apilivraria.exceptions.BookCategoryNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetBookCategoryServiceImpl implements GetBookCategoryService {

	private final BookCategoryRepository categoriaLivroRepository;

	public BookCategory find(Long id) {
		return categoriaLivroRepository.findById(id).orElseThrow(BookCategoryNotFoundException::new);
	}

}
