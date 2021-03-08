package br.com.livraria.apilivraria.purchase.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.purchase.Purchase;
import br.com.livraria.apilivraria.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListPurchaseServiceImpl implements ListPurchaseService {

	private final PurchaseRepository purchaseRepository;

	public List<Purchase> findAll() {
		return purchaseRepository.findAll();
	}

}
