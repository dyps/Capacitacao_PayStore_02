package br.com.livraria.apilivraria.purchase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.Collections;
import static br.com.livraria.apilivraria.purchase.builders.PurchaseBuilder.createPurchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.livraria.apilivraria.purchase.services.ListPagePurchaseServiceImpl;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar as compras por paginação")
public class ListPagePurchaseServiceTest {

	@Mock
	private PurchaseRepository purchaseRepository;
	private ListPagePurchaseServiceImpl listPagePurchase;

	@BeforeEach
	public void setUp() {
		this.listPagePurchase = new ListPagePurchaseServiceImpl(purchaseRepository);
	}

	@Test
	@DisplayName("Deve retornar todas as comprass com paginação")
	void shouldFindAllPurchase() {
		when(listPagePurchase.findPage(null, 0, 2))
				.thenReturn(new PageImpl<>(Collections.nCopies(2, createPurchase().build())));
		Page<Purchase> userAppPage = listPagePurchase.findPage(null, 0, 2);
		assertAll("purchase", () -> assertThat(userAppPage.getNumber(), is(0)),
				() -> assertThat(userAppPage.getSize(), is(2)),

				() -> assertThat(userAppPage.getContent().get(0).getAmount(), is(1F)),
				() -> assertThat(userAppPage.getContent().get(0).getBooks().get(0).getId(), is(1L)),
				() -> assertThat(userAppPage.getContent().get(0).getClient().getId(), is(1L)), 
				() -> assertThat(userAppPage.getContent().get(0).getCompleted(), is(true)),
				() -> assertThat(userAppPage.getContent().get(0).getDatePurchase().toString(), is("2021-01-01")),
				
				() -> assertThat(userAppPage.getContent().get(1).getAmount(), is(1F)),
				() -> assertThat(userAppPage.getContent().get(1).getBooks().get(0).getId(), is(1L)),
				() -> assertThat(userAppPage.getContent().get(1).getClient().getId(), is(1L)), 
				() -> assertThat(userAppPage.getContent().get(1).getCompleted(), is(true)),
				() -> assertThat(userAppPage.getContent().get(1).getDatePurchase().toString(), is("2021-01-01"))
				);
	}

}
