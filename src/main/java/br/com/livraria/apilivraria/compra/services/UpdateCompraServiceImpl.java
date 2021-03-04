package br.com.livraria.apilivraria.compra.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.compra.Compra;
import br.com.livraria.apilivraria.compra.CompraRepository;
import br.com.livraria.apilivraria.exceptions.CompraNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdateCompraServiceImpl implements UpdateCompraService {

	private final CompraRepository compraRepository;

	@Override
	public void update(Compra newCompra, Long id) {
		Compra compra = compraRepository.findById(id).orElseThrow(CompraNotFoundException::new);
		compra.setCliente(newCompra.getCliente());
		compra.setLivros(newCompra.getLivros());
		compra.setValorTotal(newCompra.getValorTotal());
		compra.setDataDaCompra(newCompra.getDataDaCompra());
		compra.setConcluida(newCompra.getConcluida());
		compraRepository.save(compra);
	}
}
