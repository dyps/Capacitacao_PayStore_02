package br.com.livraria.apilivraria.purchase;

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

import br.com.livraria.apilivraria.purchase.services.ListPurchaseServiceImpl;
import static br.com.livraria.apilivraria.purchase.builders.PurchaseBuilder.createPurchase;

@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por pesquisar todas as compras")
public class ListPurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;
    private ListPurchaseServiceImpl findAllPurchase;

    @BeforeEach
    public void setUp() {
        this.findAllPurchase = new ListPurchaseServiceImpl(purchaseRepository);
    }

    @Test
    @DisplayName("Deve retornar todas as compras")
    void shouldFindAllPurchase() { 

        when(purchaseRepository.findAll()).thenReturn(
                Stream.of(createPurchase().id(1L).build(),
                        createPurchase().id(2L).build(),
                        createPurchase().id(3L).build()).collect(Collectors.toList())
        );

        List <Purchase> result = this.findAllPurchase.findAll();

        assertAll("purchase",
                () -> assertThat(result.size(), is(3)),
                () -> assertThat(result.get(0).getId(), is(1L)),
                () -> assertThat(result.get(1).getId(), is(2L)),
                () -> assertThat(result.get(2).getId(), is(3L))
        );
    }
}
