package br.com.livraria.apilivraria.client.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.client.ClientRepository;
import br.com.livraria.apilivraria.exceptions.ClientNotDeletedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteClientServiceImpl implements DeleteClientService {

    private final ClientRepository clientRepository;

    @Override
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotDeletedException();
        }
        clientRepository.deleteById(id);
    }
}
