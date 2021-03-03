package br.com.livraria.apilivraria.categorialivro.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.categorialivro.CategoriaLivro;
import br.com.livraria.apilivraria.categorialivro.CategoriaLivroRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListCategoriaLivroServiceImpl implements ListCategoriaLivroService {

	private final CategoriaLivroRepository categoriaLivroRepository;

	public List<CategoriaLivro> findAll() {
		return categoriaLivroRepository.findAll();
	}

}
