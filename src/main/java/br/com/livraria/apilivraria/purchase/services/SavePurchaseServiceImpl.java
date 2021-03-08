package br.com.livraria.apilivraria.purchase.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.purchase.Purchase;
import br.com.livraria.apilivraria.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SavePurchaseServiceImpl implements SavePurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Override
    public void insert(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
}
