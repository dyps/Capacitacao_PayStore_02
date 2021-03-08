package br.com.livraria.apilivraria.purchase.services;

import br.com.livraria.apilivraria.purchase.Purchase;

@FunctionalInterface
public interface UpdatePurchaseService {

	void update(Purchase purchase, Long id);

}
