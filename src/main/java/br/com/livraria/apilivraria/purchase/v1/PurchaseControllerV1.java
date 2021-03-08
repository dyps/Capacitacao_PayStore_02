package br.com.livraria.apilivraria.purchase.v1;

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

import br.com.livraria.apilivraria.book.Book;
import br.com.livraria.apilivraria.book.services.GetBookService;
import br.com.livraria.apilivraria.client.services.GetClientService;
import br.com.livraria.apilivraria.purchase.Purchase;
import br.com.livraria.apilivraria.purchase.PurchaseDTO;
import br.com.livraria.apilivraria.purchase.services.DeletePurchaseService;
import br.com.livraria.apilivraria.purchase.services.GetPurchaseService;
import br.com.livraria.apilivraria.purchase.services.ListPagePurchaseService;
import br.com.livraria.apilivraria.purchase.services.ListPurchaseService;
import br.com.livraria.apilivraria.purchase.services.SavePurchaseService;
import br.com.livraria.apilivraria.purchase.services.UpdatePurchaseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/purchase")
public class PurchaseControllerV1 {

	private final GetPurchaseService getPurchaseService;
	private final ListPurchaseService listPurchaseService;
	private final ListPagePurchaseService listPagePurchaseService;
	private final SavePurchaseService savePurchaseService;
	private final UpdatePurchaseService updatePurchaseService;
	private final DeletePurchaseService deletePurchaseService;
	private final GetClientService getClientService;
	private final GetBookService getBookService;

	@GetMapping(value = "/{id}")
	public PurchaseDTO find(@PathVariable("id") Long id) {
		return PurchaseDTO.from(getPurchaseService.find(id));
	}

	@GetMapping
	public List<PurchaseDTO> findAll() {
		return PurchaseDTO.fromAll(listPurchaseService.findAll());
	}

	@GetMapping("/search")
	public Page<PurchaseDTO> search(@RequestParam(value = "cliente_id", required = false) Integer searchTerm,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return  PurchaseDTO.fromPage(listPagePurchaseService.findPage(searchTerm, page, size));
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping // adiciona um novo Purchase
	public void insert(@Valid @RequestBody PurchaseDTO purchaseDTO) {
		purchaseDTO.setClient(getClientService.find(purchaseDTO.getClient().getId()));
		List<Book> livros = new ArrayList<Book>();
		for (Book book : purchaseDTO.getBooks()) 
			livros .add(getBookService.find(book.getId()));
		purchaseDTO.setBooks(livros);
		savePurchaseService.insert(Purchase.to(purchaseDTO));
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(value = "/{id}") // atualizar um Purchase
	public void update(@Valid @RequestBody PurchaseDTO purchaseDTO, @PathVariable Long id) {
		purchaseDTO.setClient(getClientService.find(purchaseDTO.getClient().getId()));
		List<Book> livros = new ArrayList<Book>();
		for (Book iterable_element : purchaseDTO.getBooks()) 
			livros .add(getBookService.find(iterable_element.getId()));
		purchaseDTO.setBooks(livros);
		updatePurchaseService.update(Purchase.to(purchaseDTO), id);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}") // Deleta Purchase
	public void delete(@PathVariable Long id) {
		deletePurchaseService.delete(id);
	}

}
