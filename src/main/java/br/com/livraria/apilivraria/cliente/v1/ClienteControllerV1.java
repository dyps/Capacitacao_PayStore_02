package br.com.livraria.apilivraria.cliente.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.livraria.apilivraria.cliente.Cliente;
import br.com.livraria.apilivraria.cliente.ClienteDTO;
import br.com.livraria.apilivraria.cliente.services.DeleteClienteService;
import br.com.livraria.apilivraria.cliente.services.GetClienteService;
import br.com.livraria.apilivraria.cliente.services.ListClienteService;
import br.com.livraria.apilivraria.cliente.services.ListPageClienteService;
import br.com.livraria.apilivraria.cliente.services.SaveClienteService;
import br.com.livraria.apilivraria.cliente.services.UpdateClienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/cliente")
public class ClienteControllerV1 {

	private final GetClienteService getClienteService;
	private final ListClienteService listClienteService;
	private final ListPageClienteService listPageClienteService;
	private final SaveClienteService saveClienteService;
	private final UpdateClienteService updateClienteService;
	private final DeleteClienteService deleteClienteService;

	@GetMapping(value = "/{id}")
	public ClienteDTO find(@PathVariable("id") Long id) {
		return ClienteDTO.from(getClienteService.find(id));
	}

	@GetMapping
	public List<ClienteDTO> findAll() {
		return ClienteDTO.fromAll(listClienteService.findAll());
	}

	@GetMapping("/search")
	public Page<Cliente> search(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return listPageClienteService.findPage(nome, page, size);

	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping // adiciona um novo Cliente
	public void insert(@Valid @RequestBody ClienteDTO clienteDTO) {
		saveClienteService.insert(Cliente.to(clienteDTO));
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(value = "/{id}") // atualizar um Cliente
	public void update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
		updateClienteService.update(Cliente.to(clienteDTO), id);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}") // Deleta Cliente
	public void delete(@PathVariable Long id) {
		deleteClienteService.delete(id);
	}

}
