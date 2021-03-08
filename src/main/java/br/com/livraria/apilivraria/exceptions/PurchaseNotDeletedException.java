package br.com.livraria.apilivraria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PurchaseNotDeletedException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PurchaseNotDeletedException() {
        super("Compra não deletado porque não foi encontrada!");
    }
}
