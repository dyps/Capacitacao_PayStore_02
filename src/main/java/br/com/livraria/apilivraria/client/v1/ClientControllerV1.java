package br.com.livraria.apilivraria.client.v1;

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

import br.com.livraria.apilivraria.client.Client;
import br.com.livraria.apilivraria.client.ClientDTO;
import br.com.livraria.apilivraria.client.services.DeleteClientService;
import br.com.livraria.apilivraria.client.services.GetClientService;
import br.com.livraria.apilivraria.client.services.ListClientService;
import br.com.livraria.apilivraria.client.services.ListPageClientService;
import br.com.livraria.apilivraria.client.services.SaveClientService;
import br.com.livraria.apilivraria.client.services.UpdateClientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/client")
public class ClientControllerV1 {

	private final GetClientService getClientService;
	private final ListClientService listClientService;
	private final ListPageClientService listPageClientService;
	private final SaveClientService saveClientService;
	private final UpdateClientService updateClientService;
	private final DeleteClientService deleteClientService;

	@GetMapping(value = "/{id}")
	public ClientDTO find(@PathVariable("id") Long id) {
		return ClientDTO.from(getClientService.find(id));
	}

	@GetMapping
	public List<ClientDTO> findAll() {
		return ClientDTO.fromAll(listClientService.findAll());
	}

	@GetMapping("/search")
	public Page<ClientDTO> search(@RequestParam(value = "nome", required = false, defaultValue = "") String searchTerm,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return ClientDTO.fromPage(listPageClientService.findPage(searchTerm, page, size));

	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping // adiciona um novo Client
	public void insert(@Valid @RequestBody ClientDTO clientDTO) {
		saveClientService.insert(Client.to(clientDTO));
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(value = "/{id}") // atualizar um Client
	public void update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Long id) {
		updateClientService.update(Client.to(clientDTO), id);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}") // Deleta Client
	public void delete(@PathVariable Long id) {
		deleteClientService.delete(id);
	}

}
