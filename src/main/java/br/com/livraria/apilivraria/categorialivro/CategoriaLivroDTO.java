package br.com.livraria.apilivraria.categorialivro;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class CategoriaLivroDTO {

	private Long id;
	
	@NotEmpty
	@NotNull
	private String nome;

	
	public static CategoriaLivroDTO from(CategoriaLivro cliente) {
		return CategoriaLivroDTO
				.builder()
				.id(cliente.getId())
				.nome(cliente.getNome())
				.build();
	}

	public static List<CategoriaLivroDTO> fromAll(List<CategoriaLivro> clientes) {
	        return clientes.stream().map(CategoriaLivroDTO::from).collect(Collectors.toList());
	}

}
