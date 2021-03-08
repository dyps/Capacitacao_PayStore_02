package br.com.livraria.apilivraria.client.services;

import br.com.livraria.apilivraria.client.Client;

@FunctionalInterface
public interface UpdateClientService {

	void update(Client client, Long id);

}
