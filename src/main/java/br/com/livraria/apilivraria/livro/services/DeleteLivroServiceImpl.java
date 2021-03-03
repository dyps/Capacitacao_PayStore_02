package br.com.livraria.apilivraria.livro.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.exceptions.LivroNotDeletedException;
import br.com.livraria.apilivraria.livro.LivroRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteLivroServiceImpl implements DeleteLivroService {

	private final LivroRepository livroRepository;

	@Override
	public void delete(Long id) {
		if (!livroRepository.existsById(id)) {
			throw new LivroNotDeletedException();
		}
		livroRepository.deleteById(id);
	}
}
