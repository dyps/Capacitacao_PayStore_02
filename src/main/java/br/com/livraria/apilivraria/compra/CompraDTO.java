package br.com.livraria.apilivraria.compra;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.livraria.apilivraria.cliente.Cliente;
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
@Builder(builderClassName = "Builder")
public class CompraDTO {

	private Long id;

	@NotNull
	@Valid
	private Cliente cliente;

	@NotEmpty
	@Valid
	private List<Livro> livros;

	@NotNull
	@Positive
	private Float valorTotal;

	@NotNull
	@PastOrPresent
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataDaCompra;

	@NotNull
	private Boolean concluida;

	public static CompraDTO from(Compra compra) {

		return CompraDTO.builder()
				.id(compra.getId())
				.cliente(compra.getCliente())
				.livros(compra.getLivros())
				.valorTotal(compra.getValorTotal())
				.dataDaCompra(compra.getDataDaCompra())
				.concluida(compra.getConcluida())
				.build();
	}

	public static List<CompraDTO> fromAll(List<Compra> compras) {
		return compras.stream().map(CompraDTO::from).collect(Collectors.toList());
	}

}
