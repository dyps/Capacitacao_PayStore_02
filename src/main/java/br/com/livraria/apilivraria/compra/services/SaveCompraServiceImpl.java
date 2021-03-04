package br.com.livraria.apilivraria.compra.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.compra.Compra;
import br.com.livraria.apilivraria.compra.CompraRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveCompraServiceImpl implements SaveCompraService {

    private final CompraRepository compraRepository;

    @Override
    public void insert(Compra compra) {
        compraRepository.save(compra);
    }
}
