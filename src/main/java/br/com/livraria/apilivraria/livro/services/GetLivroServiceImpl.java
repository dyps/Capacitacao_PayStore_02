package br.com.livraria.apilivraria.livro.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.livro.Livro;
import br.com.livraria.apilivraria.livro.LivroRepository;
import br.com.livraria.apilivraria.exceptions.LivroNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetLivroServiceImpl implements GetLivroService {

    private final LivroRepository livroRepository;

    @Override
    public Livro find(Long id) {
		return livroRepository.findById(id).orElseThrow(LivroNotFoundException::new);
    }

}
