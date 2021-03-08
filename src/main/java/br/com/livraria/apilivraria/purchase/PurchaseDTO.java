package br.com.livraria.apilivraria.purchase;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.livraria.apilivraria.book.Book;
import br.com.livraria.apilivraria.client.Client;
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
public class PurchaseDTO {

	private Long id;

	@NotNull
	@Valid
	private Client client;

	@NotEmpty
	@Valid
	private List<Book> books;

	@NotNull
	@Positive
	private Float amount;

	@NotNull
	@PastOrPresent
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate datePurchase;

	@NotNull
	private Boolean completed;

	public static PurchaseDTO from(Purchase purchase) {

		return PurchaseDTO.builder()
				.id(purchase.getId())
				.client(purchase.getClient())
				.books(purchase.getBooks())
				.amount(purchase.getAmount())
				.datePurchase(purchase.getDatePurchase())
				.completed(purchase.getCompleted())
				.build();
	}

	public static List<PurchaseDTO> fromAll(List<Purchase> purchases) {
		return purchases.stream().map(PurchaseDTO::from).collect(Collectors.toList());
	}

	public static Page<PurchaseDTO> fromPage(Page<Purchase> page) {
		return page.map(PurchaseDTO::from);
	}

}
