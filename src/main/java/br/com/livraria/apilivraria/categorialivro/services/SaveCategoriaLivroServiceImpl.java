package br.com.livraria.apilivraria.categorialivro.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.categorialivro.CategoriaLivro;
import br.com.livraria.apilivraria.categorialivro.CategoriaLivroRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveCategoriaLivroServiceImpl implements SaveCategoriaLivroService {

    private final CategoriaLivroRepository categoriaLivroRepository;

    @Override
    public void insert(CategoriaLivro categoriaLivro) {
		categoriaLivroRepository.save(categoriaLivro);
    }
}
