package br.com.livraria.apilivraria.purchase.services;

import br.com.livraria.apilivraria.purchase.Purchase;

@FunctionalInterface
public interface SavePurchaseService {

	void insert(Purchase purchase);

}
