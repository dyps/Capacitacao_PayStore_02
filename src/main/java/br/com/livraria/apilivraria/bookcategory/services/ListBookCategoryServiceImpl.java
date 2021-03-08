package br.com.livraria.apilivraria.bookcategory.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.bookcategory.BookCategory;
import br.com.livraria.apilivraria.bookcategory.BookCategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListBookCategoryServiceImpl implements ListBookCategoryService {

	private final BookCategoryRepository categoriaLivroRepository;

	public List<BookCategory> findAll() {
		return categoriaLivroRepository.findAll();
	}

}
