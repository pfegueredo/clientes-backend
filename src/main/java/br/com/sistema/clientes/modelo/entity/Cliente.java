package br.com.sistema.clientes.modelo.entity;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Getter @Setter (ao invés de usar estes, usar o @Data
@Data //Anotação do Lombok para gerar Get, Sets, Construtores, ToStrings.

@NoArgsConstructor //Criar construtor sem nenhum argumento.
@AllArgsConstructor //Criar construtor com todos os argumentos.
@Builder
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;

	@Column(nullable = false, length = 11)
	@CPF
	@NotEmpty(message = "{campo.cpf.obrigatorio}")
	private String cpf;

	@Column(name = "data_Cadastro", updatable = false) //Não permitir updates
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}
}

