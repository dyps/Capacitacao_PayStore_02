package br.com.livraria.apilivraria.purchase.services;

import java.util.List;

import br.com.livraria.apilivraria.purchase.Purchase;

@FunctionalInterface
public interface ListPurchaseService {

	List<Purchase> findAll();

}
