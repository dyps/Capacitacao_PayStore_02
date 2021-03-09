package br.com.livraria.apilivraria.bookcategory.builders;

import br.com.livraria.apilivraria.bookcategory.BookCategory;

public class BookCategoryBuilder {

	public static BookCategory.Builder createBookCategory() {
		return BookCategory.builder()
				.name("Ação")
				;
	}
}
