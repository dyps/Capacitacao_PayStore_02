package br.com.livraria.apilivraria.livro.services;

import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.livro.Livro;
import br.com.livraria.apilivraria.livro.LivroRepository;
import br.com.livraria.apilivraria.exceptions.LivroNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdateLivroServiceImpl implements UpdateLivroService {

	private final LivroRepository livroRepository;

	@Override
	public void update(Livro newLivro, Long id) {
		Livro livro = livroRepository.findById(id).orElseThrow(LivroNotFoundException::new);
		livro.setTitulo(newLivro.getTitulo());
		livro.setSinopse(newLivro.getSinopse());
		livro.setIsbn(newLivro.getIsbn());
		livro.setAutor(newLivro.getAutor());
		livro.setAnoDePublicacao(newLivro.getAnoDePublicacao());
		livro.setPrecoParaVenda(newLivro.getPrecoParaVenda());
		livro.setQuantidadeDisponivel(newLivro.getQuantidadeDisponivel());
		livro.setCategoriasLivro(newLivro.getCategoriasLivro());
		livroRepository.save(livro);
	}
}
