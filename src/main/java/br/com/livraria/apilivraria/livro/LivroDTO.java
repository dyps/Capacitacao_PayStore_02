package br.com.livraria.apilivraria.livro;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.livraria.apilivraria.categorialivro.CategoriaLivro;
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
public class LivroDTO {

	private Long id;

	@NotEmpty
	private String titulo;
	@NotEmpty
	private String sinopse;
	@NotEmpty
	private String isbn;
	@NotEmpty
	private String autor;
	@NotNull
	@DateTimeFormat(pattern = "yyyy")
	private Date anoDePublicação;
	@Positive
	@NotNull
	private Float precoParaVenda;
	@Positive
	@NotNull
	private Integer quantidadeDisponivel;

	@NotEmpty
	private List<CategoriaLivro> categoriasLivro;

	public static LivroDTO from(Livro livro) {
		return LivroDTO.builder().id(livro.getId()).titulo(livro.getTitulo()).isbn(livro.getIsbn())
				.autor(livro.getAutor()).anoDePublicação(livro.getAnoDePublicação())
				.precoParaVenda(livro.getPrecoParaVenda()).quantidadeDisponivel(livro.getQuantidadeDisponivel())
				.categoriasLivro(livro.getCategoriasLivro()).sinopse(livro.getSinopse()).build();
	}

	public static List<LivroDTO> fromAll(List<Livro> livros) {
		return livros.stream().map(LivroDTO::from).collect(Collectors.toList());
	}

}
