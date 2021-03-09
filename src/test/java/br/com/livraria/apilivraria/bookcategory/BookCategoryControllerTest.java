package br.com.livraria.apilivraria.bookcategory;

import static br.com.livraria.apilivraria.bookcategory.builders.BookCategoryBuilder.createBookCategory;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.livraria.apilivraria.bookcategory.services.DeleteBookCategoryService;
import br.com.livraria.apilivraria.bookcategory.services.GetBookCategoryService;
import br.com.livraria.apilivraria.bookcategory.services.ListBookCategoryService;
import br.com.livraria.apilivraria.bookcategory.services.ListPageBookCategoryService;
import br.com.livraria.apilivraria.bookcategory.services.SaveBookCategoryService;
import br.com.livraria.apilivraria.bookcategory.services.UpdateBookCategoryService;
import br.com.livraria.apilivraria.bookcategory.v1.BookCategoryControllerV1;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookCategoryControllerV1.class)
@DisplayName("Valida funcionalidade do Controller categoria livro")
public class BookCategoryControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetBookCategoryService getBookCategoryService;
    @MockBean
    private ListBookCategoryService listBookCategoryService;
    @MockBean
    private ListPageBookCategoryService listPageBookCategoryService;
    @MockBean
    private SaveBookCategoryService saveBookCategoryService;
    @MockBean
    private UpdateBookCategoryService updateBookCategoryService;
    @MockBean
    private DeleteBookCategoryService deleteBookCategoryService;

    @Test
    @DisplayName("Pesquisa categoria de livro por id")
    void whenValidGetIdBookCategory_thenReturnsBookCategory() throws Exception { 

        when(getBookCategoryService.find(1L)).thenReturn(createBookCategory().id(1L).build());

        mockMvc.perform(get("/v1/api/bookcategory/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Ação")));
    }

    @Test
    @DisplayName("Pesquisa lista de categoria de livro")
    void whenValidListBookCategory_thenReturnsBookCategory() throws Exception { 

        when(listBookCategoryService.findAll()).thenReturn(Lists.newArrayList(
                createBookCategory().id(1L).build(), createBookCategory().id(2L).build()
        ));

        mockMvc.perform(get("/v1/api/bookcategory")
        		
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Ação")))
                
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Ação")))
                ;
    }

    @Test
    @DisplayName("Pesquisa categoria de livro com paginação")
    void whenValidListPageBookCategory_thenReturnsBookCategoryPage() throws Exception { 
        Page<BookCategory> bookcategoryPage = new PageImpl<>(Collections.singletonList(createBookCategory().id(1L).build()));

        when(listPageBookCategoryService.findPage("",0, 2)).thenReturn(bookcategoryPage);

        mockMvc.perform(get("/v1/api/bookcategory/search?page=0&size=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].name", is("Ação")))
                ;
    }

    @Test
    @DisplayName("Salva uma categoria de livro")
    void whenValidSaveBookCategory_thenReturns201() throws Exception {
        mockMvc.perform(post("/v1/api/bookcategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("bookcategoryDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Edita uma categoria de livro")
    void whenValidUpdateBookCategory_thenReturns204() throws Exception { 
        mockMvc.perform(put("/v1/api/bookcategory/{id}", 1L)
                .content(readJson("bookcategoryUpdate.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deleta categoria de livro")
    void whenValidDelete_thenReturns204() throws Exception {
        mockMvc.perform(delete("/v1/api/bookcategory/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }	

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/java/resources/json/" + file).toAbsolutePath());
        return new String(bytes);
    }
}
