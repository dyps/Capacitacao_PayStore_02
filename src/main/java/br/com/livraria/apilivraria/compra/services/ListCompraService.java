package br.com.livraria.apilivraria.compra.services;

import java.util.List;

import br.com.livraria.apilivraria.compra.Compra;

@FunctionalInterface
public interface ListCompraService {

	List<Compra> findAll();

}
