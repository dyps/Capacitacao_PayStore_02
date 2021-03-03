package br.com.livraria.apilivraria.categorialivro.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.categorialivro.CategoriaLivro;
import br.com.livraria.apilivraria.categorialivro.CategoriaLivroRepository;
import br.com.livraria.apilivraria.exceptions.CategoriaLivroNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetCategoriaLivroServiceImpl implements GetCategoriaLivroService {

	private final CategoriaLivroRepository categoriaLivroRepository;

	public CategoriaLivro find(Long id) {
		return categoriaLivroRepository.findById(id).orElseThrow(CategoriaLivroNotFoundException::new);
	}

}
