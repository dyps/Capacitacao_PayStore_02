package br.com.livraria.apilivraria.client.services;

import java.util.List;

import br.com.livraria.apilivraria.client.Client;

@FunctionalInterface
public interface ListClientService {

	List<Client> findAll();

}
