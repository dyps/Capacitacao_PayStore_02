package br.com.livraria.apilivraria.livro;

import java.io.Serializable;
import java.time.LocalDate;
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

import br.com.livraria.apilivraria.categorialivro.CategoriaLivro;
import br.com.livraria.apilivraria.compra.Compra;
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
@Table(name = "TB_Livro")
public class Livro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LivroSeq")
	@SequenceGenerator(name = "LivroSeq", sequenceName = "LIVRO_SEQ", allocationSize = 1)
	private Long id;

	private String titulo;
	private String sinopse;
	private String isbn;
	private String autor;
	private LocalDate anoDePublicacao;
	private Float precoParaVenda;
	private Integer quantidadeDisponivel;

	@ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	private List<CategoriaLivro> categoriasLivro;
	
	@JsonIgnore
	@ManyToMany
	private List<Compra> compras;

	public static Livro to(@Valid LivroDTO livroDTO) {
			return Livro.builder().id(livroDTO.getId()).titulo(livroDTO.getTitulo()).isbn(livroDTO.getIsbn())
					.autor(livroDTO.getAutor()).anoDePublicacao(livroDTO.getAnoDePublicacao())
					.precoParaVenda(livroDTO.getPrecoParaVenda()).quantidadeDisponivel(livroDTO.getQuantidadeDisponivel())
					.categoriasLivro(livroDTO.getCategoriasLivro()).sinopse(livroDTO.getSinopse()).build();
	}

	
}
