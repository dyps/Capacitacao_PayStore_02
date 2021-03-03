package br.com.livraria.apilivraria.livro.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.livro.Livro;
import br.com.livraria.apilivraria.livro.LivroRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListLivroServiceImpl implements ListLivroService {

	private final LivroRepository livroRepository;

	@Override
	public List<Livro> findAll() {
		return livroRepository.findAll();
	}

}
