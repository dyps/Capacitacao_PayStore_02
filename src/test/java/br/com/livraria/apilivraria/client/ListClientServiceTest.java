package br.com.livraria.apilivraria.client;

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

import br.com.livraria.apilivraria.client.services.ListClientServiceImpl;
import static br.com.livraria.apilivraria.client.builders.ClientBuilder.createClient;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar todos os clientes")
public class ListClientServiceTest {

    @Mock
    private ClientRepository clienteRepository;
    private ListClientServiceImpl findAllClient;

    @BeforeEach
    public void setUp() {
        this.findAllClient = new ListClientServiceImpl(clienteRepository);
    }

    @Test
    @DisplayName("Deve retornar todos os clientes")
    void shouldFindAllClient() { 

        when(clienteRepository.findAll()).thenReturn(
                Stream.of(createClient().name("Cliente nome Teste GET 01").build(),
                        createClient().name("Cliente nome Teste GET 02").build(),
                        createClient().name("Cliente nome Teste GET 03").build()).collect(Collectors.toList())
        );

        List <Client> result = this.findAllClient.findAll();

        //verificação
        assertAll("client",
                () -> assertThat(result.size(), is(3)),
                () -> assertThat(result.get(0).getName(), is("Cliente nome Teste GET 01")),
                () -> assertThat(result.get(1).getName(), is("Cliente nome Teste GET 02")),
                () -> assertThat(result.get(2).getName(), is("Cliente nome Teste GET 03"))
        );
    }
}
