package br.com.livraria.apilivraria.categorialivro;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.livraria.apilivraria.livro.Livro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(builderClassName = "Builder")
@Table(name = "TB_CategoriaLivro")
public class CategoriaLivro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CategoriaLivroSeq")
	@SequenceGenerator(name = "CategoriaLivroSeq", sequenceName = "CATEGORIALIVRO_SEQ", allocationSize = 1)
	private Long id;

	private String nome;

	@JsonIgnore
	@ManyToMany(mappedBy = "categoriasLivro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Livro> livros;
	
	public static CategoriaLivro to(@Valid CategoriaLivroDTO clienteDTO) {
		return CategoriaLivro.builder().id(clienteDTO.getId()).nome(clienteDTO.getNome()).build();
	}

}
