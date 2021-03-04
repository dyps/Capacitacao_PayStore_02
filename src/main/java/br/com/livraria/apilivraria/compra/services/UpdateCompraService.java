package br.com.livraria.apilivraria.compra.services;

import br.com.livraria.apilivraria.compra.Compra;

@FunctionalInterface
public interface UpdateCompraService {

	void update(Compra compra, Long id);

}
