package br.com.livraria.apilivraria.book;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.livraria.apilivraria.bookcategory.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class BookDTO {

	private Long id;

	@NotEmpty
	private String title;
	@NotEmpty
	private String synopsis;
	@NotEmpty
	private String isbn;
	@NotEmpty
	private String author;
	@NotNull
	@PastOrPresent
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate yearPublication;
	@Positive
	@NotNull
	private Float priceSale;
	@Positive
	@NotNull
	private Integer availableQuantity;
	@NotEmpty
	private List<BookCategory> bookCategories;

	public static BookDTO from(Book book) {

		return BookDTO.builder()
				.id(book.getId())
				.title(book.getTitle())
				.isbn(book.getIsbn())
				.author(book.getAuthor())
				.yearPublication(book.getYearPublication())
				.priceSale(book.getPriceSale())
				.availableQuantity(book.getAvailableQuantity())
				.bookCategories(book.getBookCategories())
				.synopsis(book.getSynopsis())
				.build();
	}

	public static List<BookDTO> fromAll(List<Book> books) {
		return books.stream().map(BookDTO::from).collect(Collectors.toList());
	}
	public static Page<BookDTO> fromPage(Page<Book> page) {
		return page.map(BookDTO::from);
	}

}
