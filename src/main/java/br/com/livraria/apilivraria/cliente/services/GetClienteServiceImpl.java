package br.com.livraria.apilivraria.cliente.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.cliente.Cliente;
import br.com.livraria.apilivraria.cliente.ClienteRepository;
import br.com.livraria.apilivraria.exceptions.ClienteNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetClienteServiceImpl implements GetClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente find(Long id) {
		return clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
    }

}
