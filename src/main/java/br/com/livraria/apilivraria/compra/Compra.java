package br.com.livraria.apilivraria.compra;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;

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
@Entity
@Builder(builderClassName = "Builder")
@Table(name = "TB_Compra")
public class Compra implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CompraSeq")
	@SequenceGenerator(name = "CompraSeq", sequenceName = "COMPRA_SEQ", allocationSize = 1)
	private Long id;

	@ManyToOne
	private Cliente cliente;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "tb_livro_compras", joinColumns = @JoinColumn(name = "compra_id"), inverseJoinColumns = @JoinColumn(name = "livro_id"))
	private List<Livro> livros;

	private Float valorTotal;

	private LocalDate dataDaCompra;
	private Boolean concluida;

	public static Compra to(@Valid CompraDTO livroDTO) {
		return Compra.builder().id(livroDTO.getId()).cliente(livroDTO.getCliente()).livros(livroDTO.getLivros())
				.valorTotal(livroDTO.getValorTotal()).dataDaCompra(livroDTO.getDataDaCompra())
				.concluida(livroDTO.getConcluida()).build();
	}

}
