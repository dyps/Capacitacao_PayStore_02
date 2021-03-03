package br.com.livraria.apilivraria.categorialivro.v1;

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
import br.com.livraria.apilivraria.categorialivro.CategoriaLivroDTO;
import br.com.livraria.apilivraria.categorialivro.services.DeleteCategoriaLivroService;
import br.com.livraria.apilivraria.categorialivro.services.GetCategoriaLivroService;
import br.com.livraria.apilivraria.categorialivro.services.ListCategoriaLivroService;
import br.com.livraria.apilivraria.categorialivro.services.ListPageCategoriaLivroService;
import br.com.livraria.apilivraria.categorialivro.services.SaveCategoriaLivroService;
import br.com.livraria.apilivraria.categorialivro.services.UpdateCategoriaLivroService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/categorialivro")
public class CategoriaLivroControllerV1 {

	private final GetCategoriaLivroService getCategoriaLivroService;
	private final ListCategoriaLivroService listCategoriaLivroService;
	private final ListPageCategoriaLivroService listPageCategoriaLivroService;
	private final SaveCategoriaLivroService saveCategoriaLivroService;
	private final UpdateCategoriaLivroService updateCategoriaLivroService;
	private final DeleteCategoriaLivroService deleteCategoriaLivroService;

	@GetMapping(value = "/{id}")
	public CategoriaLivroDTO find(@PathVariable("id") Long id) {
		return CategoriaLivroDTO.from(getCategoriaLivroService.find(id));
	}

	@GetMapping
	public List<CategoriaLivroDTO> findAll() {
		return CategoriaLivroDTO.fromAll(listCategoriaLivroService.findAll());
	}

	@GetMapping("/search")
	public Page<CategoriaLivro> search(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return listPageCategoriaLivroService.findPage(nome, page, size);

	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping // adiciona um novo CategoriaLivro
	public void insert(@Valid @RequestBody CategoriaLivroDTO categorialivroDTO) {
		saveCategoriaLivroService.insert(CategoriaLivro.to(categorialivroDTO));
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(value = "/{id}") // atualizar uma CategoriaLivro
	public void update(@Valid @RequestBody CategoriaLivroDTO categorialivroDTO, @PathVariable Long id) {
		updateCategoriaLivroService.update(CategoriaLivro.to(categorialivroDTO), id);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}") // Deleta CategoriaLivro
	public void delete(@PathVariable Long id) {
		deleteCategoriaLivroService.delete(id);
	}

}
