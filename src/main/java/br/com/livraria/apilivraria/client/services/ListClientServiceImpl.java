package br.com.livraria.apilivraria.client.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.client.Client;
import br.com.livraria.apilivraria.client.ClientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListClientServiceImpl implements ListClientService {

	private final ClientRepository clientRepository;

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

}
