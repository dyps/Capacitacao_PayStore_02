package br.com.livraria.apilivraria.livro.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.livro.Livro;
import br.com.livraria.apilivraria.livro.LivroRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ListPageLivroServiceImpl implements ListPageLivroService{
	
	private final LivroRepository livroRepository;
	

	 public Page<Livro> findPage(String searchTerm, Integer page, Integer size) {
		 
		 PageRequest pageRequest = PageRequest.of(
	                page,
	                size,
	                Sort.Direction.ASC,
	                "titulo");
		 return livroRepository.findAll(
	                searchTerm.toLowerCase(),
	                pageRequest);
	}
	

}
