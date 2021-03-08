package br.com.livraria.apilivraria.client;

import static br.com.livraria.apilivraria.client.builders.ClientBuilder.createClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.livraria.apilivraria.client.services.SaveClientServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por salvar um cliente")
public class SaveClientServiceTest {

    @Mock
    private ClientRepository bookRepository;
    private SaveClientServiceImpl saveClient;

    @BeforeEach
    public void setUp() {
        this.saveClient = new SaveClientServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve salvar um cliente")
    void shouldSaveClient() { 

        saveClient.insert(createClient().build());
        ArgumentCaptor<Client> captorClient = ArgumentCaptor.forClass(Client.class);
        verify(bookRepository).save(captorClient.capture());
        Client result = captorClient.getValue();
        assertAll("client",
                () -> assertThat(result.getName(), is("teste nome")),
                () -> assertThat(result.getAge(), is(22)),
                () -> assertThat(result.getSex(), is(Sex.MASCULINO)),
                () -> assertThat(result.getTelephone(), is("12345678")),
                () -> assertThat(result.getEmail(), is("test@test"))
        );
    }
}
