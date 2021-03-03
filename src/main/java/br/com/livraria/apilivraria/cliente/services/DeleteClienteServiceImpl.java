package br.com.livraria.apilivraria.cliente.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.cliente.ClienteRepository;
import br.com.livraria.apilivraria.exceptions.ClienteNotDeletedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteClienteServiceImpl implements DeleteClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public void delete(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotDeletedException();
        }
        clienteRepository.deleteById(id);
    }
}
