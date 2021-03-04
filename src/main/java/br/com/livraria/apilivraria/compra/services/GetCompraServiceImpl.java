package br.com.livraria.apilivraria.compra.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.compra.Compra;
import br.com.livraria.apilivraria.compra.CompraRepository;
import br.com.livraria.apilivraria.exceptions.CompraNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetCompraServiceImpl implements GetCompraService {

    private final CompraRepository compraRepository;

    public Compra find(Long id) {
		return compraRepository.findById(id).orElseThrow(CompraNotFoundException::new);
    }

}
