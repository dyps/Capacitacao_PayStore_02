package br.com.livraria.apilivraria.purchase;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;

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
@Entity
@Builder(builderClassName = "Builder")
@Table(name = "TB_Purchase")
public class Purchase implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PurchaseSeq")
	@SequenceGenerator(name = "PurchaseSeq", sequenceName = "PURCHASE_SEQ", allocationSize = 1)
	private Long id;

	@ManyToOne
	private Client client;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "tb_book_purchases", joinColumns = @JoinColumn(name = "purchase_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<Book> books;

	private Float amount;

	private LocalDate datePurchase;
	private Boolean completed;

	public static Purchase to(@Valid PurchaseDTO purchaseDTO) {
		return Purchase.builder()
				.id(purchaseDTO.getId())
				.client(purchaseDTO.getClient())
				.books(purchaseDTO.getBooks())
				.amount(purchaseDTO.getAmount())
				.datePurchase(purchaseDTO.getDatePurchase())
				.completed(purchaseDTO.getCompleted())
				.build();
	}

}
