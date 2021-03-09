package br.com.livraria.apilivraria.book;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.Collections;
import static br.com.livraria.apilivraria.book.builders.BookBuilder.createBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.livraria.apilivraria.book.services.ListPageBookServiceImpl;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar livros por paginação")
public class ListPageBookServiceTest {

	@Mock
	private BookRepository bookRepository;
	private ListPageBookServiceImpl listPageBook;

	@BeforeEach
	public void setUp() {
		this.listPageBook = new ListPageBookServiceImpl(bookRepository);
	}

	@Test
	@DisplayName("Deve retornar todos os livros com paginação")
	void shouldFindAllBook() {
		when(listPageBook.findPage("", 0, 2))
				.thenReturn(new PageImpl<>(Collections.nCopies(2, createBook().build())));
		Page<Book> userAppPage = listPageBook.findPage("", 0, 2);
		assertAll("book", () -> assertThat(userAppPage.getNumber(), is(0)),
				() -> assertThat(userAppPage.getSize(), is(2)),
				
        		() -> assertThat(userAppPage.getContent().get(0).getTitle(), is("teste titulo")),
        		() -> assertThat(userAppPage.getContent().get(0).getAuthor(), is("yaggo")),
        		() -> assertThat(userAppPage.getContent().get(0).getAvailableQuantity(), is(2)),
        		() -> assertThat(userAppPage.getContent().get(0).getIsbn(), is("1234")),
        		() -> assertThat(userAppPage.getContent().get(0).getPriceSale(), is(5F)),
        		() -> assertThat(userAppPage.getContent().get(0).getSynopsis(), is("teste sinopse")),
        		() -> assertThat(userAppPage.getContent().get(0).getBookCategories().get(0).getId(), is(1L)),
        		() -> assertThat(userAppPage.getContent().get(0).getYearPublication().toString(), is("2020-02-02")),
        		
        		() -> assertThat(userAppPage.getContent().get(1).getTitle(), is("teste titulo")),
        		() -> assertThat(userAppPage.getContent().get(1).getAuthor(), is("yaggo")),
        		() -> assertThat(userAppPage.getContent().get(1).getAvailableQuantity(), is(2)),
        		() -> assertThat(userAppPage.getContent().get(1).getIsbn(), is("1234")),
        		() -> assertThat(userAppPage.getContent().get(1).getPriceSale(), is(5F)),
        		() -> assertThat(userAppPage.getContent().get(1).getSynopsis(), is("teste sinopse")),
        		() -> assertThat(userAppPage.getContent().get(1).getBookCategories().get(0).getId(), is(1L)),
        		() -> assertThat(userAppPage.getContent().get(1).getYearPublication().toString(), is("2020-02-02"))
				
				);
	}

}
