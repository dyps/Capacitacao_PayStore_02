package br.com.livraria.apilivraria.cliente.services;

import org.springframework.data.domain.Page;

import br.com.livraria.apilivraria.cliente.Cliente;

@FunctionalInterface
public interface ListPageClienteService {

	Page<Cliente> findPage(String searchTerm, Integer page, Integer size);

}