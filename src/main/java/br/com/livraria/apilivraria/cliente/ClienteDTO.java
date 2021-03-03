package br.com.livraria.apilivraria.cliente;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
public class ClienteDTO {

	private Long id;

	@NotEmpty
	@NotNull
	private String nome;
	@Positive
	@NotNull
	private Integer idade;
	@Size(min = 8)
	@NotEmpty
	private String telefone;
	@NotEmpty
	@Email
	private String email;
	@NotNull
	private Sexo sexo;

	public static ClienteDTO from(Cliente cliente) {
		return ClienteDTO
				.builder()
				.id(cliente.getId())
				.nome(cliente.getNome())
				.idade(cliente.getIdade())
				.telefone(cliente.getTelefone())
				.email(cliente.getEmail())
				.sexo(cliente.getSexo())
				.build();
	}

	public static List<ClienteDTO> fromAll(List<Cliente> clientes) {
	        return clientes.stream().map(ClienteDTO::from).collect(Collectors.toList());
	}

}
