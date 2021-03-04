package br.com.livraria.apilivraria.compra.services;

import org.springframework.data.domain.Page;

import br.com.livraria.apilivraria.compra.Compra;

@FunctionalInterface
public interface ListPageCompraService {

	Page<Compra> findPage(Integer searchTerm, Integer page, Integer size);

}