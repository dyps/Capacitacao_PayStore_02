package br.com.livraria.apilivraria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LivroNotDeletedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LivroNotDeletedException() {
		super("Livro não deletado!");
	}
}
