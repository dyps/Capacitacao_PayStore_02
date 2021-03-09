package br.com.livraria.apilivraria.purchase;

import static br.com.livraria.apilivraria.purchase.builders.PurchaseBuilder.createPurchase;
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

import br.com.livraria.apilivraria.book.services.GetBookService;
import br.com.livraria.apilivraria.client.services.GetClientService;
import br.com.livraria.apilivraria.purchase.services.DeletePurchaseService;
import br.com.livraria.apilivraria.purchase.services.GetPurchaseService;
import br.com.livraria.apilivraria.purchase.services.ListPagePurchaseService;
import br.com.livraria.apilivraria.purchase.services.ListPurchaseService;
import br.com.livraria.apilivraria.purchase.services.SavePurchaseService;
import br.com.livraria.apilivraria.purchase.services.UpdatePurchaseService;
import br.com.livraria.apilivraria.purchase.v1.PurchaseControllerV1;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PurchaseControllerV1.class)
@DisplayName("Valida funcionalidade do Controller compra")
public class PurchaseControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPurchaseService getPurchaseService;
    @MockBean
    private ListPurchaseService listPurchaseService;
    @MockBean
    private ListPagePurchaseService listPagePurchaseService;
    @MockBean
    private SavePurchaseService savePurchaseService;
    @MockBean
    private UpdatePurchaseService updatePurchaseService;
    @MockBean
    private DeletePurchaseService deletePurchaseService;
    @MockBean
    private GetClientService getClientService;
    @MockBean
	private GetBookService getBookService;

    @Test
    @DisplayName("Pesquisa compra por id")
    void whenValidGetIdPurchase_thenReturnsPurchase() throws Exception { //pesquisa por purchasee

        when(getPurchaseService.find(1L)).thenReturn(createPurchase().id(1L).build());

        mockMvc.perform(get("/v1/api/purchase/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.client.id", is(1)))
                .andExpect(jsonPath("$.datePurchase", is("2021-01-01")))
                .andExpect(jsonPath("$.amount", is(1.0)))
                .andExpect(jsonPath("$.completed", is(true)))
                .andExpect(jsonPath("$.books[0].id", is(1)))
                ;
    }

    @Test
    @DisplayName("Pesquisa lista de compras")
    void whenValidListPurchase_thenReturnsPurchase() throws Exception { //pesquisa todos os purchasee

        when(listPurchaseService.findAll()).thenReturn(Lists.newArrayList(
                createPurchase().id(1L).build(), createPurchase().id(2L).build()
        ));

        mockMvc.perform(get("/v1/api/purchase")
        		
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].client.id", is(1)))
                .andExpect(jsonPath("$[0].datePurchase", is("2021-01-01")))
                .andExpect(jsonPath("$[0].amount", is(1.0)))
                .andExpect(jsonPath("$[0].completed", is(true)))
                .andExpect(jsonPath("$[0].books[0].id", is(1)))
                
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].client.id", is(1)))
                .andExpect(jsonPath("$[1].datePurchase", is("2021-01-01")))
                .andExpect(jsonPath("$[1].amount", is(1.0)))
                .andExpect(jsonPath("$[1].completed", is(true)))
                .andExpect(jsonPath("$[1].books[0].id", is(1)))
                ;
    }

    @Test
    @DisplayName("Pesquisa compra com paginação")
    void whenValidListPagePurchase_thenReturnsPurchasePage() throws Exception { //pesquisa todos os purchasee
        Page<Purchase> purchasePage = new PageImpl<>(Collections.singletonList(createPurchase().id(1L).build()));

        when(listPagePurchaseService.findPage(null,0, 2)).thenReturn(purchasePage);

        mockMvc.perform(get("/v1/api/purchase/search?page=0&size=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(1)))
                
                .andExpect(jsonPath("$.content[0].client.id", is(1)))
                .andExpect(jsonPath("$.content[0].datePurchase", is("2021-01-01")))
                .andExpect(jsonPath("$.content[0].amount", is(1.0)))
                .andExpect(jsonPath("$.content[0].completed", is(true)))
                .andExpect(jsonPath("$.content[0].books[0].id", is(1)))
                ;
    }

    @Test
    @DisplayName("Salva uma compra")
    void whenValidSavePurchase_thenReturns201() throws Exception { //insere purchase
        mockMvc.perform(post("/v1/api/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("purchaseDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Edita uma compra")
    void whenValidUpdatePurchase_thenReturns204() throws Exception { //atualiza um purchase
        mockMvc.perform(put("/v1/api/purchase/{id}", 1L)
                .content(readJson("purchaseUpdate.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deleta compra")
    void whenValidDelete_thenReturns204() throws Exception { // deleta purchasee
        mockMvc.perform(delete("/v1/api/purchase/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }	

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/java/resources/json/" + file).toAbsolutePath());
        return new String(bytes);
    }
}
