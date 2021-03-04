package br.com.livraria.apilivraria.compra.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.compra.CompraRepository;
import br.com.livraria.apilivraria.exceptions.CompraNotDeletedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteCompraServiceImpl implements DeleteCompraService {

    private final CompraRepository compraRepository;

    @Override
    public void delete(Long id) {
        if (!compraRepository.existsById(id)) {
            throw new CompraNotDeletedException();
        }
        compraRepository.deleteById(id);
    }
}
