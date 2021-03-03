package br.com.livraria.apilivraria.cliente.services;

import br.com.livraria.apilivraria.cliente.Cliente;

@FunctionalInterface
public interface GetClienteService {

	Cliente find(Long id);

}
