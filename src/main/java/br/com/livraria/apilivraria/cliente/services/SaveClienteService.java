package br.com.livraria.apilivraria.cliente.services;

import br.com.livraria.apilivraria.cliente.Cliente;

@FunctionalInterface
public interface SaveClienteService {

	void insert(Cliente cliente);

}
