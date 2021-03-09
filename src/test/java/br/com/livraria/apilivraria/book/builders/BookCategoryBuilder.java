package br.com.livraria.apilivraria.book.builders;

import static br.com.livraria.apilivraria.book.builders.BookBuilder.createBook;

import org.assertj.core.util.Lists;

import br.com.livraria.apilivraria.bookcategory.BookCategory;

public class BookCategoryBuilder {

	public static BookCategory.Builder createBookCategory() {
		return BookCategory.builder()
				.name("Ação")
				.books(Lists.newArrayList(createBook().id(1L).build()))
				;
	}
}
