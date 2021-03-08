package br.com.livraria.apilivraria.book.v1;

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

import br.com.livraria.apilivraria.bookcategory.BookCategory;
import br.com.livraria.apilivraria.bookcategory.services.GetBookCategoryService;
import br.com.livraria.apilivraria.book.Book;
import br.com.livraria.apilivraria.book.BookDTO;
import br.com.livraria.apilivraria.book.services.DeleteBookService;
import br.com.livraria.apilivraria.book.services.GetBookService;
import br.com.livraria.apilivraria.book.services.ListBookService;
import br.com.livraria.apilivraria.book.services.ListPageBookService;
import br.com.livraria.apilivraria.book.services.SaveBookService;
import br.com.livraria.apilivraria.book.services.UpdateBookService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/book")
public class BookControllerV1 {

	private final GetBookService getBookService;
	private final GetBookCategoryService getCategoriaBookService;
	private final ListBookService listBookService;
	private final SaveBookService saveBookService;
	private final UpdateBookService updateBookService;
	private final DeleteBookService deleteBookService;
	private final ListPageBookService listPageBookService;

	@GetMapping(value = "/{id}")
	public BookDTO find(@PathVariable("id") Long id) {
		return BookDTO.from(getBookService.find(id));
	}

	@GetMapping("/search")
	public Page<BookDTO> search(@RequestParam(value = "titulo", required = false, defaultValue = "") String searchTerm,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return BookDTO.fromPage(listPageBookService.findPage(searchTerm, page, size));
	}

	@GetMapping
	public List<BookDTO> findAll() {
		return BookDTO.fromAll(listBookService.findAll());
	}

	@GetMapping(value = "/categoria/{id}")
	public List<BookDTO> findAllPorCat(@PathVariable("id") Long id) {
		return BookDTO.fromAll(getCategoriaBookService.find(id).getBooks());
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping // adiciona um novo Book
	public void insert(@Valid @RequestBody BookDTO bookDTO) {
		List<BookCategory> categories = new ArrayList<BookCategory>();
		for (BookCategory bookCategory : bookDTO.getBookCategories())
			categories.add(getCategoriaBookService.find(bookCategory.getId()));
		bookDTO.setBookCategories(categories);
		saveBookService.insert(Book.to(bookDTO));
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(value = "/{id}") // atualizar um Book
	public void update(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long id) {
		for (BookCategory bookCategory : bookDTO.getBookCategories())
			getCategoriaBookService.find(bookCategory.getId());
		updateBookService.update(Book.to(bookDTO), id);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}") // Deleta Book
	public void delete(@PathVariable Long id) {
		deleteBookService.delete(id);
	}

}
