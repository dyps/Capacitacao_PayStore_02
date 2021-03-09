package br.com.livraria.apilivraria.client;

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

import br.com.livraria.apilivraria.client.services.DeleteClientServiceImpl;
import br.com.livraria.apilivraria.exceptions.ClientNotDeletedException;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por deletar um client")
public class DeleteClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    private DeleteClientServiceImpl deleteClient;

    @BeforeEach
    public void setUp() {
        this.deleteClient = new DeleteClientServiceImpl(clientRepository);
    }

    @Test
    @DisplayName("Deve deletar um cliente")
    void shouldClientDeleted() {
        when(clientRepository.existsById(1L)).thenReturn(true);
        deleteClient.delete(1L);
        verify(clientRepository).existsById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o cliente não puder ser excluido")
    void shouldThrowClientNotDeletedException() {
        lenient().when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ClientNotDeletedException.class, () -> this.deleteClient.delete(1L));
    }
}
