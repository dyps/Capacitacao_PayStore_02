package br.com.livraria.apilivraria.cliente.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.cliente.Cliente;
import br.com.livraria.apilivraria.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListClienteServiceImpl implements ListClienteService {

	private final ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

}
