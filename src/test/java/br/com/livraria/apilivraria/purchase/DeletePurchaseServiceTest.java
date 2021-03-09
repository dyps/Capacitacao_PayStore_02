package br.com.livraria.apilivraria.purchase;

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

import br.com.livraria.apilivraria.purchase.services.DeletePurchaseServiceImpl;
import br.com.livraria.apilivraria.exceptions.PurchaseNotDeletedException;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por deletar uma compra")
public class DeletePurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;
    private DeletePurchaseServiceImpl deletePurchase;

    @BeforeEach
    public void setUp() {
        this.deletePurchase = new DeletePurchaseServiceImpl(purchaseRepository);
    }

    @Test
    @DisplayName("Deve deletar uma compra")
    void shouldPurchaseDeleted() {
        when(purchaseRepository.existsById(1L)).thenReturn(true);
        deletePurchase.delete(1L);
        verify(purchaseRepository).existsById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando a compra não puder ser excluida")
    void shouldThrowPurchaseNotDeletedException() {
        lenient().when(purchaseRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(PurchaseNotDeletedException.class, () -> this.deletePurchase.delete(1L));
    }
}
