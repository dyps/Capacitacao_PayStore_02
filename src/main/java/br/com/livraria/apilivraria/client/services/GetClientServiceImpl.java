package br.com.livraria.apilivraria.client.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.client.Client;
import br.com.livraria.apilivraria.client.ClientRepository;
import br.com.livraria.apilivraria.exceptions.ClientNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetClientServiceImpl implements GetClientService {

    private final ClientRepository clientRepository;

    public Client find(Long id) {
		return clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
    }

}
