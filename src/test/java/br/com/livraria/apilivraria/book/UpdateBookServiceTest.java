package br.com.livraria.apilivraria.book;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static br.com.livraria.apilivraria.book.builders.BookBuilder.createBook;
import br.com.livraria.apilivraria.book.services.UpdateBookServiceImpl;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por atualizar livro")
public class UpdateBookServiceTest {

    @Mock
    private BookRepository bookRepository;
    private UpdateBookServiceImpl updateBook;

    @BeforeEach
    public void setUp() {
        this.updateBook = new UpdateBookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve atualizar um livro")
    void shouldUpdateBook() { // testando atualizar livro

        when(bookRepository.findById(1L)).thenReturn(Optional.of(createBook().id(1L).build()));

        updateBook.update(createBook().title("teste update").build(), 1L);

        ArgumentCaptor<Book> captorBook = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(captorBook.capture());
        
        Book result = captorBook.getValue();

        assertAll("book",
        		() -> assertThat(result.getTitle(), is("teste update")),
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

