package br.com.livraria.apilivraria.client.builders;

import br.com.livraria.apilivraria.client.Client;
import br.com.livraria.apilivraria.client.Sex;

public class ClientBuilder {
	
	 public static Client.Builder createClient() {
	        return Client
	                .builder()
	                .name("teste nome")
	                .age(22)
	                .sex(Sex.MASCULINO)
	                .telephone("12345678")
	                .email("test@test")
	                ;
	    }

}
