package br.com.livraria.apilivraria.purchase.services;

import br.com.livraria.apilivraria.purchase.Purchase;

@FunctionalInterface
public interface GetPurchaseService {

	Purchase find(Long id);

}
