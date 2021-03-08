package br.com.livraria.apilivraria.purchase.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.purchase.Purchase;
import br.com.livraria.apilivraria.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListPagePurchaseServiceImpl implements ListPagePurchaseService {

	private final PurchaseRepository purchaseRepository;

	public Page<Purchase> findPage(Integer searchTerm, Integer page, Integer size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");
		return (searchTerm != null) ? purchaseRepository.findAll(searchTerm, pageRequest)
				: purchaseRepository.findAll(pageRequest);
	}

}
