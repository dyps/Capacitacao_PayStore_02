package br.com.livraria.apilivraria.cliente.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.cliente.Cliente;
import br.com.livraria.apilivraria.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveClienteServiceImpl implements SaveClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public void insert(Cliente cliente) {
        clienteRepository.save(cliente);
    }
}
