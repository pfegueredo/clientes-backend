package br.com.sistema.clientes.modelo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ServicoPrestado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	private String descricao;

	@ManyToOne //Muitos Serviços para 1 cliente
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@Column
	private BigDecimal valor;

	@Column
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

}
