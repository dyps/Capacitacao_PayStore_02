package br.com.livraria.apilivraria.purchase;

import static br.com.livraria.apilivraria.purchase.builders.PurchaseBuilder.createPurchase;
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

import br.com.livraria.apilivraria.purchase.services.GetPurchaseServiceImpl;
import br.com.livraria.apilivraria.exceptions.PurchaseNotFoundException;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar uma compra")
public class GetPurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;
    private GetPurchaseServiceImpl findPurchase;

    @BeforeEach
    public void setUp() {
        this.findPurchase = new GetPurchaseServiceImpl(purchaseRepository);
    }

    @Test
    @DisplayName("Deve retornar uma compra")
    void shouldFindByIdPurchase() { 

        when(purchaseRepository.findById(anyLong())).thenReturn(
                Optional.of(createPurchase().completed(false).build())
        );

        Purchase result = this.findPurchase.find(1L);
        assertAll("purchase",
        		() -> assertThat(result.getAmount(), is(1F)),
                () -> assertThat(result.getBooks().get(0).getId(), is(1L)),
                () -> assertThat(result.getClient().getId(), is(1L)),
                () -> assertThat(result.getCompleted(), is(false)),
                () -> assertThat(result.getDatePurchase().toString(), is("2021-01-01"))
        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando a compra não for encontrada")
    void shouldThrowPurchaseNotFoundException() {
        when(purchaseRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(PurchaseNotFoundException.class, () -> this.findPurchase.find(1L));
    }
}
