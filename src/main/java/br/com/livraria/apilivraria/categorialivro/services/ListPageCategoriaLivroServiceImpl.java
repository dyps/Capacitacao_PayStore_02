package br.com.livraria.apilivraria.categorialivro.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.livraria.apilivraria.categorialivro.CategoriaLivro;
import br.com.livraria.apilivraria.categorialivro.CategoriaLivroRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ListPageCategoriaLivroServiceImpl implements ListPageCategoriaLivroService{
	
	private final CategoriaLivroRepository categorialivroRepository;
	

	@Override
	 public Page<CategoriaLivro> findPage(String searchTerm, int page, int size) {
		 
		 PageRequest pageRequest = PageRequest.of(
	                page,
	                size,
	                Sort.Direction.ASC,
	                "nome");
		 return categorialivroRepository.findAll(
	                searchTerm.toLowerCase(),
	                pageRequest);
	}


}
