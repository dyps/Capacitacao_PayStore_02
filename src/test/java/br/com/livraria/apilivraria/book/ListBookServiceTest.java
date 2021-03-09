package br.com.livraria.apilivraria.book;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.livraria.apilivraria.book.services.ListBookServiceImpl;
import static br.com.livraria.apilivraria.book.builders.BookBuilder.createBook;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar todos os livros")
public class ListBookServiceTest {

    @Mock
    private BookRepository bookRepository;
    private ListBookServiceImpl findAllBook;

    @BeforeEach
    public void setUp() {
        this.findAllBook = new ListBookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve retornar todos os livros")
    void shouldFindAllBook() { 

        when(bookRepository.findAll()).thenReturn(
                Stream.of(createBook().title("Book title Teste GET 01").build(),
                        createBook().title("Book title Teste GET 02").build(),
                        createBook().title("Book title Teste GET 03").build()).collect(Collectors.toList())
        );

        List <Book> result = this.findAllBook.findAll();

        //verificação
        assertAll("book",
                () -> assertThat(result.size(), is(3)),
                () -> assertThat(result.get(0).getTitle(), is("Book title Teste GET 01")),
                () -> assertThat(result.get(1).getTitle(), is("Book title Teste GET 02")),
                () -> assertThat(result.get(2).getTitle(), is("Book title Teste GET 03"))
        );
    }
}
