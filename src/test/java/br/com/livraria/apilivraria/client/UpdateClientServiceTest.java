package br.com.livraria.apilivraria.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static br.com.livraria.apilivraria.client.builders.ClientBuilder.createClient;
import br.com.livraria.apilivraria.client.services.UpdateClientServiceImpl;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por atualizar cliente")
public class UpdateClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    private UpdateClientServiceImpl updateClient;

    @BeforeEach
    public void setUp() {
        this.updateClient = new UpdateClientServiceImpl(clientRepository);
    }

    @Test
    @DisplayName("Deve atualizar um cliente")
    void shouldUpdateClient() { // testando atualizar cliente

        when(clientRepository.findById(1L)).thenReturn(Optional.of(createClient().id(1L).build()));

        updateClient.update(createClient().name("teste update").build(), 1L);

        ArgumentCaptor<Client> captorClient = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).save(captorClient.capture());
        
        Client result = captorClient.getValue();

        assertAll("client",
                () -> assertThat(result.getName(), is("teste update")),
                () -> assertThat(result.getAge(), is(22)),
                () -> assertThat(result.getSex(), is(Sex.MASCULINO)),
                () -> assertThat(result.getTelephone(), is("12345678")),
                () -> assertThat(result.getEmail(), is("test@test"))
        );
    }
}

