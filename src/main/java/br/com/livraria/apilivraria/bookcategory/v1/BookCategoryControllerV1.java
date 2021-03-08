package br.com.livraria.apilivraria.bookcategory.v1;

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

import br.com.livraria.apilivraria.bookcategory.BookCategory;
import br.com.livraria.apilivraria.bookcategory.BookCategoryDTO;
import br.com.livraria.apilivraria.bookcategory.services.DeleteBookCategoryService;
import br.com.livraria.apilivraria.bookcategory.services.GetBookCategoryService;
import br.com.livraria.apilivraria.bookcategory.services.ListBookCategoryService;
import br.com.livraria.apilivraria.bookcategory.services.ListPageBookCategoryService;
import br.com.livraria.apilivraria.bookcategory.services.SaveBookCategoryService;
import br.com.livraria.apilivraria.bookcategory.services.UpdateBookCategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/bookcategory")
public class BookCategoryControllerV1 {

	private final GetBookCategoryService getBookCategoryService;
	private final ListBookCategoryService listBookCategoryService;
	private final ListPageBookCategoryService listPageBookCategoryService;
	private final SaveBookCategoryService saveBookCategoryService;
	private final UpdateBookCategoryService updateBookCategoryService;
	private final DeleteBookCategoryService deleteBookCategoryService;

	@GetMapping(value = "/{id}")
	public BookCategoryDTO find(@PathVariable("id") Long id) {
		return BookCategoryDTO.from(getBookCategoryService.find(id));
	}

	@GetMapping
	public List<BookCategoryDTO> findAll() {
		return BookCategoryDTO.fromAll(listBookCategoryService.findAll());
	}

	@GetMapping("/search")
	public Page<BookCategoryDTO> search(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return BookCategoryDTO.fromPage(listPageBookCategoryService.findPage(nome, page, size));

	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping // adiciona um novo BookCategory
	public void insert(@Valid @RequestBody BookCategoryDTO bookcategoryDTO) {
		saveBookCategoryService.insert(BookCategory.to(bookcategoryDTO));
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(value = "/{id}") // atualizar uma BookCategory
	public void update(@Valid @RequestBody BookCategoryDTO bookcategoryDTO, @PathVariable Long id) {
		updateBookCategoryService.update(BookCategory.to(bookcategoryDTO), id);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}") // Deleta BookCategory
	public void delete(@PathVariable Long id) {
		deleteBookCategoryService.delete(id);
	}

}
