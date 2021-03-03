package br.com.livraria.apilivraria.cliente.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.cliente.Cliente;
import br.com.livraria.apilivraria.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ListPageClienteServiceImpl implements ListPageClienteService{
	
	private final ClienteRepository clienteRepository;
	

	 public Page<Cliente> findPage(String searchTerm, Integer page, Integer size) {
		 
		 PageRequest pageRequest = PageRequest.of(
	                page,
	                size,
	                Sort.Direction.ASC,
	                "nome");
		 return clienteRepository.findAll(
	                searchTerm.toLowerCase(),
	                pageRequest);
	}
	

}
