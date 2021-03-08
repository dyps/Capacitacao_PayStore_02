package br.com.livraria.apilivraria.bookcategory.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.bookcategory.BookCategory;
import br.com.livraria.apilivraria.bookcategory.BookCategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveBookCategoryServiceImpl implements SaveBookCategoryService {

    private final BookCategoryRepository categoriaLivroRepository;

    @Override
    public void insert(BookCategory categoriaLivro) {
		categoriaLivroRepository.save(categoriaLivro);
    }
}
