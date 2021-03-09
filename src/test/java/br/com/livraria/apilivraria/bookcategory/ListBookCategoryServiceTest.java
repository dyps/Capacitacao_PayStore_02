package br.com.livraria.apilivraria.bookcategory;

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

import br.com.livraria.apilivraria.bookcategory.services.ListBookCategoryServiceImpl;
import static br.com.livraria.apilivraria.bookcategory.builders.BookCategoryBuilder.createBookCategory;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar todas as categorias de livro")
public class ListBookCategoryServiceTest {

    @Mock
    private BookCategoryRepository bookcategoryRepository;
    private ListBookCategoryServiceImpl findAllBookCategory;

    @BeforeEach
    public void setUp() {
        this.findAllBookCategory = new ListBookCategoryServiceImpl(bookcategoryRepository);
    }

    @Test
    @DisplayName("Deve retornar todas as categorias de livro")
    void shouldFindAllBookCategory() { 

        when(bookcategoryRepository.findAll()).thenReturn(
                Stream.of(createBookCategory().name("Categoria de livro nome Teste GET 01").build(),
                        createBookCategory().name("Categoria de livro nome Teste GET 02").build(),
                        createBookCategory().name("Categoria de livro nome Teste GET 03").build()).collect(Collectors.toList())
        );

        List <BookCategory> result = this.findAllBookCategory.findAll();

        assertAll("bookcategory",
                () -> assertThat(result.size(), is(3)),
                () -> assertThat(result.get(0).getName(), is("Categoria de livro nome Teste GET 01")),
                () -> assertThat(result.get(1).getName(), is("Categoria de livro nome Teste GET 02")),
                () -> assertThat(result.get(2).getName(), is("Categoria de livro nome Teste GET 03"))
        );
    }
}
