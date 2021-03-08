package br.com.livraria.apilivraria.client;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;

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
public class ClientDTO {

	private Long id;

	@NotEmpty
	@NotNull
	private String name;
	@Positive
	@NotNull
	private Integer age;
	@Size(min = 8)
	@NotEmpty
	private String telephone;
	@NotEmpty
	@Email
	private String email;
	@NotNull
	private Sex sex;

	public static ClientDTO from(Client client) {
		return ClientDTO
				.builder()
				.id(client.getId())
				.name(client.getName())
				.age(client.getAge())
				.telephone(client.getTelephone())
				.email(client.getEmail())
				.sex(client.getSex())
				.build();
	}

	public static List<ClientDTO> fromAll(List<Client> clients) {
	        return clients.stream().map(ClientDTO::from).collect(Collectors.toList());
	}
	public static Page<ClientDTO> fromPage(Page<Client> page) {
		return page.map(ClientDTO::from);
	}

}
