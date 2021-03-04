package br.com.livraria.apilivraria.compra.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.compra.Compra;
import br.com.livraria.apilivraria.compra.CompraRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListPageCompraServiceImpl implements ListPageCompraService {

	private final CompraRepository compraRepository;

	public Page<Compra> findPage(Integer searchTerm, Integer page, Integer size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");
		return (searchTerm != null) ? compraRepository.findAll(searchTerm, pageRequest)
				: compraRepository.findAll(pageRequest);
	}

}
