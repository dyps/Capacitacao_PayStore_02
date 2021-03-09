package br.com.livraria.apilivraria.book.builders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.livraria.apilivraria.book.Book;
import br.com.livraria.apilivraria.bookcategory.BookCategory;

public class BookBuilder {
	
	 public static Book.Builder createBook() {
	        List<BookCategory> listBook= new ArrayList<BookCategory>();
			listBook.add(BookCategory.builder().id(1L).name("Ação").build());
			return Book
	                .builder()
	                .title("teste titulo")
	                .availableQuantity(2)
	                .priceSale(5F)
	                .synopsis("teste sinopse")
	                .yearPublication(LocalDate.of(2020, 02, 02))
	                .author("yaggo")
	                .isbn("1234")
	                .bookCategories(listBook)
	                ;
	    }

}