package com.augusto.model.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.augusto.model.Arquivo;
import com.augusto.model.enums.SituacaoConta;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
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
	
	private String contaDeSaida;
	
	private boolean estaVencida;
	private boolean venceHoje;

	public ListagemDeContaDTO(Long idConta, String descricaoConta, Date vencimento, String comentarios,
			SituacaoConta situacaoConta, String comentarioDePagamento, String contaDeSaida) {
		this.idConta = idConta;
		this.descricaoConta = descricaoConta;
		this.vencimento = vencimento;
		this.comentarios = comentarios;
		this.situacaoConta = situacaoConta;
		this.comentarioDePagamento = comentarioDePagamento;
		this.contaDeSaida = contaDeSaida;
		
		verificaVencimento();
		
	}

	private void verificaVencimento() {
		LocalDate hoje = LocalDate.now();
		LocalDate vencimentoLocalDate = this.vencimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		this.estaVencida = vencimentoLocalDate.isBefore(hoje);
		this.venceHoje = vencimentoLocalDate.isEqual(hoje);
	}
	
	public void definirArquivos(Arquivo arquivo) {
		if (arquivo.eBoleto()) {
			this.idBoleto = arquivo.getId();
			this.nomeBoleto =  arquivo.getNome();
		}
		if (arquivo.eComprovante()) {
			this.idComprovante = arquivo.getId();
			this.nomeComprovante = arquivo.getNome();
		}
	}
}
