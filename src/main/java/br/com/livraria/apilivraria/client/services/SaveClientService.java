package br.com.livraria.apilivraria.client.services;

import br.com.livraria.apilivraria.client.Client;

@FunctionalInterface
public interface SaveClientService {

	void insert(Client client);

}
