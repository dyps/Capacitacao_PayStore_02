package br.com.livraria.apilivraria.cliente.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.cliente.Cliente;
import br.com.livraria.apilivraria.cliente.ClienteRepository;
import br.com.livraria.apilivraria.exceptions.ClienteNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdateClienteServiceImpl implements UpdateClienteService {

	private final ClienteRepository clienteRepository;

	@Override
	public void update(Cliente newCliente, Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
		cliente.setNome(newCliente.getNome());
		cliente.setIdade(newCliente.getIdade());
		cliente.setTelefone(newCliente.getTelefone());
		cliente.setEmail(newCliente.getEmail());
		cliente.setSexo(newCliente.getSexo());
		clienteRepository.save(cliente);
	}
}
