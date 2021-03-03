package br.com.livraria.apilivraria.cliente;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.livraria.apilivraria.compra.Compra;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(builderClassName = "Builder")
@Table(name = "TB_Cliente")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClienteSeq")
	@SequenceGenerator(name = "ClienteSeq", sequenceName = "CLIENTE_SEQ", allocationSize = 1)
	private Long id;

	private String nome;
	private Integer idade;
	private String telefone;
	@Email
	private String email;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Compra> compras;

	public static Cliente to(@Valid ClienteDTO clienteDTO) {
		return Cliente.builder().id(clienteDTO.getId()).nome(clienteDTO.getNome()).idade(clienteDTO.getIdade())
				.telefone(clienteDTO.getTelefone()).email(clienteDTO.getEmail()).sexo(clienteDTO.getSexo()).build();
	}

}
