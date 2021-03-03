package br.com.livraria.apilivraria.categorialivro.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.categorialivro.CategoriaLivro;
import br.com.livraria.apilivraria.categorialivro.CategoriaLivroRepository;
import br.com.livraria.apilivraria.exceptions.CategoriaLivroNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdateCategoriaLivroServiceImpl implements UpdateCategoriaLivroService {

	private final CategoriaLivroRepository categoriaLivroRepository;

	@Override
	public void update(CategoriaLivro newCategoriaLivro, Long id) {
		CategoriaLivro categoriaLivro = categoriaLivroRepository.findById(id).orElseThrow(CategoriaLivroNotFoundException::new);
		categoriaLivro.setNome(newCategoriaLivro.getNome());
		categoriaLivroRepository.save(categoriaLivro);
	}
}
