package br.com.livraria.apilivraria.purchase.services;

import org.springframework.data.domain.Page;

import br.com.livraria.apilivraria.purchase.Purchase;

@FunctionalInterface
public interface ListPagePurchaseService {

	Page<Purchase> findPage(Integer searchTerm, Integer page, Integer size);

}