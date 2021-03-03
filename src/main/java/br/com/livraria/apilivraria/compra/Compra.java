package br.com.livraria.apilivraria.compra;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
	@ManyToMany(mappedBy = "compras")
	private List<Livro> livros;

	private Float valorTotal;
	@Temporal(TemporalType.DATE)
	private Date dataDaCompra;
	private Boolean concluida;
	
}
