package br.com.livraria.apilivraria.cliente.services;

import br.com.livraria.apilivraria.cliente.Cliente;

@FunctionalInterface
public interface UpdateClienteService {

	void update(Cliente cliente, Long id);

}
