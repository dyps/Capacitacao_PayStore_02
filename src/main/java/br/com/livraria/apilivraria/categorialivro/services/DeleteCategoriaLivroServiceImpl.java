package br.com.livraria.apilivraria.categorialivro.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.categorialivro.CategoriaLivroRepository;
import br.com.livraria.apilivraria.exceptions.CategoriaLivroNotDeletedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteCategoriaLivroServiceImpl implements DeleteCategoriaLivroService {

	private final CategoriaLivroRepository categoriaLivroRepository;

	@Override
	public void delete(Long id) {
		if (!categoriaLivroRepository.existsById(id)) {
			throw new CategoriaLivroNotDeletedException();
		}
		categoriaLivroRepository.deleteById(id);
	}
}
