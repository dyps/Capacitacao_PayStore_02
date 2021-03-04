package br.com.livraria.apilivraria.compra.services;

import br.com.livraria.apilivraria.compra.Compra;

@FunctionalInterface
public interface SaveCompraService {

	void insert(Compra compra);

}
