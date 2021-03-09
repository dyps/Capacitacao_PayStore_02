package br.com.livraria.apilivraria.purchase;

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
import static br.com.livraria.apilivraria.purchase.builders.PurchaseBuilder.createPurchase;
import br.com.livraria.apilivraria.purchase.services.UpdatePurchaseServiceImpl;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por atualizar compra")
public class UpdatePurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;
    private UpdatePurchaseServiceImpl updatePurchase;

    @BeforeEach
    public void setUp() {
        this.updatePurchase = new UpdatePurchaseServiceImpl(purchaseRepository);
    }

    @Test
    @DisplayName("Deve atualizar uma compra")
    void shouldUpdatePurchase() { 

        when(purchaseRepository.findById(1L)).thenReturn(Optional.of(createPurchase().id(1L).build()));

        updatePurchase.update(createPurchase().completed(false).build(), 1L);

        ArgumentCaptor<Purchase> captorPurchase = ArgumentCaptor.forClass(Purchase.class);
        verify(purchaseRepository).save(captorPurchase.capture());
        
        Purchase result = captorPurchase.getValue();

        assertAll("purchase",
                () -> assertThat(result.getAmount(), is(1F)),
                () -> assertThat(result.getBooks().get(0).getId(), is(1L)),
                () -> assertThat(result.getClient().getId(), is(1L)),
                () -> assertThat(result.getCompleted(), is(false)),
                () -> assertThat(result.getDatePurchase().toString(), is("2021-01-01"))
        );
    }
}

