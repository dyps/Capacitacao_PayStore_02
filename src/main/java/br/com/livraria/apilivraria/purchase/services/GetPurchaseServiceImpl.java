package br.com.livraria.apilivraria.purchase.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.purchase.Purchase;
import br.com.livraria.apilivraria.purchase.PurchaseRepository;
import br.com.livraria.apilivraria.exceptions.PurchaseNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetPurchaseServiceImpl implements GetPurchaseService {

    private final PurchaseRepository purchaseRepository;

    public Purchase find(Long id) {
		return purchaseRepository.findById(id).orElseThrow(PurchaseNotFoundException::new);
    }

}
