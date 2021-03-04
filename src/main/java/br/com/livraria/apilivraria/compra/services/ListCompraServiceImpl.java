package br.com.livraria.apilivraria.compra.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.compra.Compra;
import br.com.livraria.apilivraria.compra.CompraRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListCompraServiceImpl implements ListCompraService {

	private final CompraRepository compraRepository;

	public List<Compra> findAll() {
		return compraRepository.findAll();
	}

}
