package br.com.livraria.apilivraria.livro.v1;

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

import br.com.livraria.apilivraria.categorialivro.CategoriaLivro;
import br.com.livraria.apilivraria.categorialivro.services.GetCategoriaLivroService;
import br.com.livraria.apilivraria.livro.Livro;
import br.com.livraria.apilivraria.livro.LivroDTO;
import br.com.livraria.apilivraria.livro.services.DeleteLivroService;
import br.com.livraria.apilivraria.livro.services.GetLivroService;
import br.com.livraria.apilivraria.livro.services.ListLivroService;
import br.com.livraria.apilivraria.livro.services.ListPageLivroService;
import br.com.livraria.apilivraria.livro.services.SaveLivroService;
import br.com.livraria.apilivraria.livro.services.UpdateLivroService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/livro")
public class LivroControllerV1 {

	private final GetLivroService getLivroService;
	private final GetCategoriaLivroService getCategoriaLivroService;
	private final ListLivroService listLivroService;
	private final SaveLivroService saveLivroService;
	private final UpdateLivroService updateLivroService;
	private final DeleteLivroService deleteLivroService;
	private final ListPageLivroService listPageLivroService;

	@GetMapping(value = "/{id}")
	public LivroDTO find(@PathVariable("id") Long id) {
		return LivroDTO.from(getLivroService.find(id));
	}

	@GetMapping("/search")
	public Page<Livro> search(@RequestParam(value = "titulo", required = false, defaultValue = "") String titulo,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return listPageLivroService.findPage(titulo, page, size);
	}

	@GetMapping
	public List<LivroDTO> findAll() {
		return LivroDTO.fromAll(listLivroService.findAll());
	}

	@GetMapping(value = "/categoria/{id}")
	public List<LivroDTO> findAllPorCat(@PathVariable("id") Long id) {
		return LivroDTO.fromAll(getCategoriaLivroService.find(id).getLivros());
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping // adiciona um novo Livro
	public void insert(@Valid @RequestBody LivroDTO livroDTO) {
		List<CategoriaLivro> categorias = new ArrayList<CategoriaLivro>();
		for (CategoriaLivro iterable_element : livroDTO.getCategoriasLivro())
			categorias.add(getCategoriaLivroService.find(iterable_element.getId()));
		livroDTO.setCategoriasLivro(categorias);
		saveLivroService.insert(Livro.to(livroDTO));
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(value = "/{id}") // atualizar um Livro
	public void update(@Valid @RequestBody LivroDTO livroDTO, @PathVariable Long id) {
		for (CategoriaLivro iterable_element : livroDTO.getCategoriasLivro())
			getCategoriaLivroService.find(iterable_element.getId());
		updateLivroService.update(Livro.to(livroDTO), id);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}") // Deleta Livro
	public void delete(@PathVariable Long id) {
		deleteLivroService.delete(id);
	}

}
