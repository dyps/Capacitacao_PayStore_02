package br.com.livraria.apilivraria.compra.v1;

import java.util.ArrayList;
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

import br.com.livraria.apilivraria.cliente.services.GetClienteService;
import br.com.livraria.apilivraria.compra.Compra;
import br.com.livraria.apilivraria.compra.CompraDTO;
import br.com.livraria.apilivraria.compra.services.DeleteCompraService;
import br.com.livraria.apilivraria.compra.services.GetCompraService;
import br.com.livraria.apilivraria.compra.services.ListCompraService;
import br.com.livraria.apilivraria.compra.services.ListPageCompraService;
import br.com.livraria.apilivraria.compra.services.SaveCompraService;
import br.com.livraria.apilivraria.compra.services.UpdateCompraService;
import br.com.livraria.apilivraria.livro.Livro;
import br.com.livraria.apilivraria.livro.services.GetLivroService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/compra")
public class CompraControllerV1 {

	private final GetCompraService getCompraService;
	private final ListCompraService listCompraService;
	private final ListPageCompraService listPageCompraService;
	private final SaveCompraService saveCompraService;
	private final UpdateCompraService updateCompraService;
	private final DeleteCompraService deleteCompraService;
	private final GetClienteService getClienteService;
	private final GetLivroService getLivroService;

	@GetMapping(value = "/{id}")
	public CompraDTO find(@PathVariable("id") Long id) {
		return CompraDTO.from(getCompraService.find(id));
	}

	@GetMapping
	public List<CompraDTO> findAll() {
		return CompraDTO.fromAll(listCompraService.findAll());
	}

	@GetMapping("/search")
	public Page<Compra> search(@RequestParam(value = "cliente_id", required = false) Integer id,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return listPageCompraService.findPage(id, page, size);

	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping // adiciona um novo Compra
	public void insert(@Valid @RequestBody CompraDTO compraDTO) {
		compraDTO.setCliente(getClienteService.find(compraDTO.getCliente().getId()));
		List<Livro> livros = new ArrayList<Livro>();
		for (Livro iterable_element : compraDTO.getLivros()) 
			livros .add(getLivroService.find(iterable_element.getId()));
		compraDTO.setLivros(livros);
		saveCompraService.insert(Compra.to(compraDTO));
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(value = "/{id}") // atualizar um Compra
	public void update(@Valid @RequestBody CompraDTO compraDTO, @PathVariable Long id) {
		compraDTO.setCliente(getClienteService.find(compraDTO.getCliente().getId()));
		List<Livro> livros = new ArrayList<Livro>();
		for (Livro iterable_element : compraDTO.getLivros()) 
			livros .add(getLivroService.find(iterable_element.getId()));
		compraDTO.setLivros(livros);
		updateCompraService.update(Compra.to(compraDTO), id);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}") // Deleta Compra
	public void delete(@PathVariable Long id) {
		deleteCompraService.delete(id);
	}

}
