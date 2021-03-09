package br.com.livraria.apilivraria.purchase;

import static br.com.livraria.apilivraria.purchase.builders.PurchaseBuilder.createPurchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.livraria.apilivraria.purchase.services.SavePurchaseServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por salvar uma compra")
public class SavePurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;
    private SavePurchaseServiceImpl savePurchase;

    @BeforeEach
    public void setUp() {
        this.savePurchase = new SavePurchaseServiceImpl(purchaseRepository);
    }

    @Test
    @DisplayName("Deve salvar uma compra")
    void shouldSavePurchase() { 

        savePurchase.insert(createPurchase().build());
        ArgumentCaptor<Purchase> captorPurchase = ArgumentCaptor.forClass(Purchase.class);
        verify(purchaseRepository).save(captorPurchase.capture());
        Purchase result = captorPurchase.getValue();
        assertAll("purchase",
                () -> assertThat(result.getAmount(), is(1F)),
                () -> assertThat(result.getBooks().get(0).getId(), is(1L)),
                () -> assertThat(result.getClient().getId(), is(1L)),
                () -> assertThat(result.getCompleted(), is(true)),
                () -> assertThat(result.getDatePurchase().toString(), is("2021-01-01"))
        );
    }
}
