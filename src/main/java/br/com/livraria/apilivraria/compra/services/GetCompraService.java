package br.com.livraria.apilivraria.compra.services;

import br.com.livraria.apilivraria.compra.Compra;

@FunctionalInterface
public interface GetCompraService {

	Compra find(Long id);

}
