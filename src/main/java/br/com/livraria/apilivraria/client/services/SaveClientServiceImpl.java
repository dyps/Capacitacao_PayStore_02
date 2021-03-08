package br.com.livraria.apilivraria.client.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.client.Client;
import br.com.livraria.apilivraria.client.ClientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveClientServiceImpl implements SaveClientService {

    private final ClientRepository clientRepository;

    @Override
    public void insert(Client client) {
        clientRepository.save(client);
    }
}
