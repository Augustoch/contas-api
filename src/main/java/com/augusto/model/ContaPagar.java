package com.augusto.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.boot.context.properties.ConstructorBinding;

import com.augusto.model.enums.SituacaoConta;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContaPagar {
	@Id
	@GeneratedValue
	private Long id;

	private String descricao;

	@OneToMany(mappedBy = "contaPagar")
	@Cascade(CascadeType.ALL)
	private Collection<Arquivo> arquivo;

	private Date vencimento;

	@Column(length = 500)
	private String comentarios;

	@Enumerated(EnumType.ORDINAL)
	private SituacaoConta situacaoConta;

	@OneToOne
	private DadoContaBancaria contaDeSaida;

	@Column(length = 500)
	private String comentarioDePagamento;
	
	@OneToOne
	private Empresa empresaReponsavelConta;
	
	@OneToOne
	private Empresa empresaPagamentoDaConta;
	
	@Column(columnDefinition = "DATE")
	private Date dataPagamento;
	
	@Column(columnDefinition = "DATE")
	private Date criacao;
	
	@OneToOne
	private Usuario criador;
}
