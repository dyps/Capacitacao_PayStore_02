package br.com.livraria.apilivraria.client.services;

import org.springframework.data.domain.Page;

import br.com.livraria.apilivraria.client.Client;

@FunctionalInterface
public interface ListPageClientService {

	Page<Client> findPage(String searchTerm, Integer page, Integer size);

}