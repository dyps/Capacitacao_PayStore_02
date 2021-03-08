package br.com.livraria.apilivraria.purchase.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.purchase.PurchaseRepository;
import br.com.livraria.apilivraria.exceptions.PurchaseNotDeletedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeletePurchaseServiceImpl implements DeletePurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Override
    public void delete(Long id) {
        if (!purchaseRepository.existsById(id)) {
            throw new PurchaseNotDeletedException();
        }
        purchaseRepository.deleteById(id);
    }
}
