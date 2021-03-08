package br.com.livraria.apilivraria.client;

import static br.com.livraria.apilivraria.client.builders.ClientBuilder.createClient;
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

import br.com.livraria.apilivraria.client.services.GetClientServiceImpl;
import br.com.livraria.apilivraria.exceptions.ClientNotFoundException;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar um cliente")
public class GetClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    private GetClientServiceImpl findClient;

    @BeforeEach
    public void setUp() {
        this.findClient = new GetClientServiceImpl(clientRepository);
    }

    @Test
    @DisplayName("Deve retornar um cliente")
    void shouldFindByIdClient() { 

        when(clientRepository.findById(anyLong())).thenReturn(
                Optional.of(createClient().name("Nome Teste GET").build())
        );

        Client result = this.findClient.find(1L);

        //verificação
        assertAll("client",
                () -> assertThat(result.getName(), is("Nome Teste GET")),
                () -> assertThat(result.getAge(), is(22)),
                () -> assertThat(result.getSex(), is(Sex.MASCULINO)),
                () -> assertThat(result.getTelephone(), is("12345678")),
                () -> assertThat(result.getEmail(), is("test@test"))
        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando o cliente não for encontrado")
    void shouldThrowClientNotFoundException() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class, () -> this.findClient.find(1L));
    }
}
