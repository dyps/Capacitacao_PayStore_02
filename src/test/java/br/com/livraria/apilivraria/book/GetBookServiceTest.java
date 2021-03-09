package br.com.livraria.apilivraria.book;

import static br.com.livraria.apilivraria.book.builders.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.livraria.apilivraria.book.services.GetBookServiceImpl;
import br.com.livraria.apilivraria.exceptions.BookNotFoundException;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar um livro")
public class GetBookServiceTest {

    @Mock
    private BookRepository bookRepository;
    private GetBookServiceImpl findBook;

    @BeforeEach
    public void setUp() {
        this.findBook = new GetBookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve retornar um livro")
    void shouldFindByIdBook() { 

        when(bookRepository.findById(anyLong())).thenReturn(
                Optional.of(createBook().title("Title Teste GET").build())
        );

        Book result = this.findBook.find(1L);
        assertAll("book",
        		() -> assertThat(result.getTitle(), is("Title Teste GET")),
        		() -> assertThat(result.getAuthor(), is("yaggo")),
        		() -> assertThat(result.getAvailableQuantity(), is(2)),
        		() -> assertThat(result.getIsbn(), is("1234")),
        		() -> assertThat(result.getPriceSale(), is(5F)),
        		() -> assertThat(result.getSynopsis(), is("teste sinopse")),
        		() -> assertThat(result.getBookCategories().get(0).getId(), is(1L)),
        		() -> assertThat(result.getYearPublication().toString(), is("2020-02-02"))


        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando o livro não for encontrado")
    void shouldThrowBookNotFoundException() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> this.findBook.find(1L));
    }
}
