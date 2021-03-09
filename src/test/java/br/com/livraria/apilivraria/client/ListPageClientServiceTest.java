package br.com.livraria.apilivraria.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.Collections;
import static br.com.livraria.apilivraria.client.builders.ClientBuilder.createClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.livraria.apilivraria.client.services.ListPageClientServiceImpl;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar clientes por paginação")
public class ListPageClientServiceTest {

	@Mock
	private ClientRepository clientRepository;
	private ListPageClientServiceImpl listPageClient;

	@BeforeEach
	public void setUp() {
		this.listPageClient = new ListPageClientServiceImpl(clientRepository);
	}

	@Test
	@DisplayName("Deve retornar todos os clientes com paginação")
	void shouldFindAllClient() {
		when(listPageClient.findPage("", 0, 2))
				.thenReturn(new PageImpl<>(Collections.nCopies(2, createClient().build())));
		Page<Client> userAppPage = listPageClient.findPage("", 0, 2);
		assertAll("client", () -> assertThat(userAppPage.getNumber(), is(0)),
				() -> assertThat(userAppPage.getSize(), is(2)),
				() -> assertThat(userAppPage.getContent().get(0).getName(), is("teste nome")),
				() -> assertThat(userAppPage.getContent().get(0).getAge(), is(22)),
				() -> assertThat(userAppPage.getContent().get(0).getSex(), is(Sex.MASCULINO)),
				() -> assertThat(userAppPage.getContent().get(0).getTelephone(), is("12345678")),
				() -> assertThat(userAppPage.getContent().get(0).getEmail(), is("test@test")),

				() -> assertThat(userAppPage.getContent().get(1).getName(), is("teste nome")),
				() -> assertThat(userAppPage.getContent().get(1).getAge(), is(22)),
				() -> assertThat(userAppPage.getContent().get(1).getSex(), is(Sex.MASCULINO)),
				() -> assertThat(userAppPage.getContent().get(1).getTelephone(), is("12345678")),
				() -> assertThat(userAppPage.getContent().get(1).getEmail(), is("test@test")));
	}

}
