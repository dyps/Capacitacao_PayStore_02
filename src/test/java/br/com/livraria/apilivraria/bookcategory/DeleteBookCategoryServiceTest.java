package br.com.livraria.apilivraria.bookcategory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.livraria.apilivraria.bookcategory.services.DeleteBookCategoryServiceImpl;
import br.com.livraria.apilivraria.exceptions.BookCategoryNotDeletedException;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por deletar uma categoria de livro")
public class DeleteBookCategoryServiceTest {

    @Mock
    private BookCategoryRepository bookcategoryRepository;
    private DeleteBookCategoryServiceImpl deleteBookCategory;

    @BeforeEach
    public void setUp() {
        this.deleteBookCategory = new DeleteBookCategoryServiceImpl(bookcategoryRepository);
    }

    @Test
    @DisplayName("Deve deletar uma categoria de livro")
    void shouldBookCategoryDeleted() {
        when(bookcategoryRepository.existsById(1L)).thenReturn(true);
        deleteBookCategory.delete(1L);
        verify(bookcategoryRepository).existsById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando a categoria de livro não puder ser excluido")
    void shouldThrowBookCategoryNotDeletedException() {
        lenient().when(bookcategoryRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookCategoryNotDeletedException.class, () -> this.deleteBookCategory.delete(1L));
    }
}
