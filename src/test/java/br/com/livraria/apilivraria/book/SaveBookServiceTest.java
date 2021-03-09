package br.com.livraria.apilivraria.book;

import static br.com.livraria.apilivraria.book.builders.BookBuilder.createBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.livraria.apilivraria.book.services.SaveBookServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por salvar um livro")
public class SaveBookServiceTest {

    @Mock
    private BookRepository bookRepository;
    private SaveBookServiceImpl saveBook;

    @BeforeEach
    public void setUp() {
        this.saveBook = new SaveBookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    void shouldSaveBook() { 

        saveBook.insert(createBook().build());
        ArgumentCaptor<Book> captorBook = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(captorBook.capture());
        Book result = captorBook.getValue();
        assertAll("book",
        		() -> assertThat(result.getTitle(), is("teste titulo")),
        		() -> assertThat(result.getAuthor(), is("yaggo")),
        		() -> assertThat(result.getAvailableQuantity(), is(2)),
        		() -> assertThat(result.getIsbn(), is("1234")),
        		() -> assertThat(result.getPriceSale(), is(5F)),
        		() -> assertThat(result.getSynopsis(), is("teste sinopse")),
        		() -> assertThat(result.getBookCategories().get(0).getId(), is(1L)),
        		() -> assertThat(result.getYearPublication().toString(), is("2020-02-02"))
        );
    }
}
