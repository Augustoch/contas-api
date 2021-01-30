package com.augusto.model.dto;

import java.util.Date;

import com.augusto.model.enums.SituacaoConta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListagemDeContaDTO {
	private Long idConta;
	private String descricaoConta;
	private Date vencimento;
	private String comentarios;
	private SituacaoConta situacaoConta;
	private String comentarioDePagamento;

	private Long idBoleto;
	private String nomeBoleto;
	private Long idComprovante;
	private String nomeComprovante;

	public ListagemDeContaDTO(Long idConta, String descricaoConta, Date vencimento, String comentarios,
			SituacaoConta situacaoConta, String comentarioDePagamento) {
		this.idConta = idConta;
		this.descricaoConta = descricaoConta;
		this.vencimento = vencimento;
		this.comentarios = comentarios;
		this.situacaoConta = situacaoConta;
		this.comentarioDePagamento = comentarioDePagamento;

	}
}
