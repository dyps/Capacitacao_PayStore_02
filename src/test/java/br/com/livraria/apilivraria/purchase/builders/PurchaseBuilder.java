package br.com.livraria.apilivraria.purchase.builders;

import br.com.livraria.apilivraria.book.Book;
import br.com.livraria.apilivraria.bookcategory.BookCategory;
import br.com.livraria.apilivraria.client.Client;
import br.com.livraria.apilivraria.purchase.Purchase;


import java.time.LocalDate;
import java.util.List;

public class PurchaseBuilder {

	public static Purchase.Builder createPurchase() {
		return Purchase.builder()
				.client(Client.builder().id(1L).build())
				.datePurchase(LocalDate.of(2021, 01, 01))
				.amount(1F)
				.completed(true)
				.books(List.of(Book.builder()
						.id(1L)
						.bookCategories(List.of(BookCategory.builder()
								.id(1L)
								.build()))
						.build()))
				;
	}
}
