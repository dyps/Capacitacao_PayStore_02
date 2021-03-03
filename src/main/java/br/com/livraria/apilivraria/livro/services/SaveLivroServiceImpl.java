package br.com.livraria.apilivraria.livro.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.livro.Livro;
import br.com.livraria.apilivraria.livro.LivroRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveLivroServiceImpl implements SaveLivroService {

    private final LivroRepository livroRepository;

    @Override
    public void insert(Livro livro) {
        livroRepository.save(livro);
    }
}
