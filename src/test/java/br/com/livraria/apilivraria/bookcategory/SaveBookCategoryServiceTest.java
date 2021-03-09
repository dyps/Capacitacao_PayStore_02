package br.com.livraria.apilivraria.bookcategory;

import static br.com.livraria.apilivraria.bookcategory.builders.BookCategoryBuilder.createBookCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.livraria.apilivraria.bookcategory.services.SaveBookCategoryServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por salvar uma categoria de livro")
public class SaveBookCategoryServiceTest {

    @Mock
    private BookCategoryRepository bookCategoryRepository;
    private SaveBookCategoryServiceImpl saveBookCategory;

    @BeforeEach
    public void setUp() {
        this.saveBookCategory = new SaveBookCategoryServiceImpl(bookCategoryRepository);
    }

    @Test
    @DisplayName("Deve salvar uma categoria de livro")
    void shouldSaveBookCategory() { 

        saveBookCategory.insert(createBookCategory().build());
        ArgumentCaptor<BookCategory> captorBookCategory = ArgumentCaptor.forClass(BookCategory.class);
        verify(bookCategoryRepository).save(captorBookCategory.capture());
        BookCategory result = captorBookCategory.getValue();
        assertAll("bookcategory",
                () -> assertThat(result.getName(), is("Ação"))
        );
    }
}
