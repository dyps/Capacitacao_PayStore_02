package br.com.livraria.apilivraria.client;

import static br.com.livraria.apilivraria.client.builders.ClientBuilder.createClient;
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

import br.com.livraria.apilivraria.client.services.DeleteClientService;
import br.com.livraria.apilivraria.client.services.GetClientService;
import br.com.livraria.apilivraria.client.services.ListClientService;
import br.com.livraria.apilivraria.client.services.ListPageClientService;
import br.com.livraria.apilivraria.client.services.SaveClientService;
import br.com.livraria.apilivraria.client.services.UpdateClientService;
import br.com.livraria.apilivraria.client.v1.ClientControllerV1;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ClientControllerV1.class)
@DisplayName("Valida funcionalidade do Controller Client")
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetClientService getClientService;
    @MockBean
    private ListClientService listClientService;
    @MockBean
    private ListPageClientService listPageClientService;
    @MockBean
    private SaveClientService saveClientService;
    @MockBean
    private UpdateClientService updateClientService;
    @MockBean
    private DeleteClientService deleteClientService;

    @Test
    @DisplayName("Pesquisa cliente por id")
    void whenValidGetIdClient_thenReturnsClient() throws Exception { //pesquisa por cliente

        when(getClientService.find(1L)).thenReturn(createClient().id(1L).build());

        mockMvc.perform(get("/v1/api/client/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("teste nome")))
                .andExpect(jsonPath("$.age", is(22)))
                .andExpect(jsonPath("$.sex", is("MASCULINO")))
                .andExpect(jsonPath("$.telephone", is("12345678")))
                .andExpect(jsonPath("$.email", is("test@test")));
    }

    @Test
    @DisplayName("Pesquisa lista de clientes")
    void whenValidListClient_thenReturnsClient() throws Exception { //pesquisa todos os cliente

        when(listClientService.findAll()).thenReturn(Lists.newArrayList(
                createClient().id(1L).build(), createClient().id(2L).build()
        ));

        mockMvc.perform(get("/v1/api/client")
        		
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("teste nome")))
                .andExpect(jsonPath("$[0].age", is(22)))
                .andExpect(jsonPath("$[0].sex", is("MASCULINO")))
                .andExpect(jsonPath("$[0].telephone", is("12345678")))
                .andExpect(jsonPath("$[0].email", is("test@test")))
                
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("teste nome")))
                .andExpect(jsonPath("$[1].age", is(22)))
                .andExpect(jsonPath("$[1].sex", is("MASCULINO")))
                .andExpect(jsonPath("$[1].telephone", is("12345678")))
                .andExpect(jsonPath("$[1].email", is("test@test")))
                ;
    }

    @Test
    @DisplayName("Pesquisa cliente com paginação")
    void whenValidListPageClient_thenReturnsClientPage() throws Exception { //pesquisa todos os cliente
        Page<Client> clientPage = new PageImpl<>(Collections.singletonList(createClient().id(1L).build()));

        when(listPageClientService.findPage("",0, 2)).thenReturn(clientPage);

        mockMvc.perform(get("/v1/api/client/search?page=0&size=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].name", is("teste nome")))
                .andExpect(jsonPath("$.content[0].age", is(22)))
                .andExpect(jsonPath("$.content[0].sex", is("MASCULINO")))
                .andExpect(jsonPath("$.content[0].telephone", is("12345678")))
                .andExpect(jsonPath("$.content[0].email", is("test@test")))
                ;
    }

    @Test
    @DisplayName("Salva um cliente")
    void whenValidSaveClient_thenReturns201() throws Exception { //insere client
        mockMvc.perform(post("/v1/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("clientDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Edita um cliente")
    void whenValidUpdateClient_thenReturns204() throws Exception { //atualiza um client
        mockMvc.perform(put("/v1/api/client/{id}", 1L)
                .content(readJson("clientUpdate.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deleta cliente")
    void whenValidDelete_thenReturns204() throws Exception { // deleta cliente
        mockMvc.perform(delete("/v1/api/client/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }	

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/java/resources/json/" + file).toAbsolutePath());
        return new String(bytes);
    }
}
