package br.com.livraria.apilivraria.bookcategory;

import static br.com.livraria.apilivraria.bookcategory.builders.BookCategoryBuilder.createBookCategory;
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

import br.com.livraria.apilivraria.bookcategory.services.GetBookCategoryServiceImpl;
import br.com.livraria.apilivraria.exceptions.BookCategoryNotFoundException;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar uma categoria de livro")
public class GetBookCategoryServiceTest {

    @Mock
    private BookCategoryRepository bookcategoryRepository;
    private GetBookCategoryServiceImpl findBookCategory;

    @BeforeEach
    public void setUp() {
        this.findBookCategory = new GetBookCategoryServiceImpl(bookcategoryRepository);
    }

    @Test
    @DisplayName("Deve retornar uma categoria deivro")
    void shouldFindByIdBookCategory() { 

        when(bookcategoryRepository.findById(anyLong())).thenReturn(
                Optional.of(createBookCategory().name("Nome Teste GET").build())
        );

        BookCategory result = this.findBookCategory.find(1L);
        assertAll("bookcategory",
                () -> assertThat(result.getName(), is("Nome Teste GET"))
        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando a categoria de livro não for encontrado")
    void shouldThrowBookCategoryNotFoundException() {
        when(bookcategoryRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookCategoryNotFoundException.class, () -> this.findBookCategory.find(1L));
    }
}
