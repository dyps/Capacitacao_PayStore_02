package br.com.livraria.apilivraria.book;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.livraria.apilivraria.bookcategory.BookCategory;
import br.com.livraria.apilivraria.purchase.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(builderClassName = "Builder")
@Table(name = "TB_Book")
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BookSeq")
	@SequenceGenerator(name = "BookSeq", sequenceName = "BOOK_SEQ", allocationSize = 1)
	@NotNull // para validar ao ser puxado pela compra
	private Long id;

	private String title ;
	private String synopsis;
	private String isbn;
	private String author;
	private LocalDate yearPublication ;
	private Float priceSale ;
	private Integer availableQuantity;

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	private List<BookCategory> bookCategories;

	@JsonIgnore
	@ManyToMany(mappedBy = "books",cascade = CascadeType.REMOVE)
	private List<Purchase> purchases;

	public static Book to(@Valid BookDTO bookDTO) {
		return Book.builder()
				.id(bookDTO.getId())
				.title(bookDTO.getTitle())
				.isbn(bookDTO.getIsbn())
				.author(bookDTO.getAuthor())
				.yearPublication(bookDTO.getYearPublication())
				.priceSale(bookDTO.getPriceSale())
				.availableQuantity(bookDTO.getAvailableQuantity())
				.bookCategories(bookDTO.getBookCategories())
				.synopsis(bookDTO.getSynopsis())
				.build();
	}

}
