package br.com.livraria.apilivraria.cliente.services;

import java.util.List;

import br.com.livraria.apilivraria.cliente.Cliente;

@FunctionalInterface
public interface ListClienteService {

	List<Cliente> findAll();

}
