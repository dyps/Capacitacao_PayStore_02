package br.com.livraria.apilivraria.bookcategory.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.bookcategory.BookCategory;
import br.com.livraria.apilivraria.bookcategory.BookCategoryRepository;
import br.com.livraria.apilivraria.exceptions.BookCategoryNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdateBookCategoryServiceImpl implements UpdateBookCategoryService {

	private final BookCategoryRepository categoriaLivroRepository;

	@Override
	public void update(BookCategory newBookCategory, Long id) {
		BookCategory categoriaLivro = categoriaLivroRepository.findById(id).orElseThrow(BookCategoryNotFoundException::new);
		categoriaLivro.setName(newBookCategory.getName());
		categoriaLivroRepository.save(categoriaLivro);
	}
}
