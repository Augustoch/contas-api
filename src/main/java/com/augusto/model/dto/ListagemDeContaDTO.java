package com.augusto.model.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.augusto.model.Arquivo;
import com.augusto.model.enums.SituacaoConta;
import com.augusto.util.DateUtil;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class ListagemDeContaDTO {
	private Long idConta;
	private String descricaoConta;
	private LocalDate vencimento;
	private LocalDate pagamento;
	private String comentarios;
	private SituacaoConta situacaoConta;
	private String comentarioDePagamento;

	private Long idBoleto;
	private String nomeBoleto;
	private Long idComprovante;
	private String nomeComprovante;

	private String contaDeSaida;

	private String empresaResponsavel;
	private String empresaPagamento;

	private boolean estaVencida;
	private boolean venceHoje;

	public ListagemDeContaDTO(Long idConta, String descricaoConta, Date vencimento, String comentarios,
			SituacaoConta situacaoConta, String comentarioDePagamento, String contaDeSaida, String empresaResponsavel,
			String empresaPagamento, Date pagamento) {
		this.idConta = idConta;
		this.descricaoConta = descricaoConta;
		this.vencimento = DateUtil.dateToLocalDate(vencimento);
		this.pagamento = DateUtil.dateToLocalDate(pagamento);
		this.comentarios = comentarios;
		this.situacaoConta = situacaoConta;
		this.comentarioDePagamento = comentarioDePagamento;
		this.contaDeSaida = contaDeSaida;
		this.empresaResponsavel = empresaResponsavel;
		this.empresaPagamento = empresaPagamento;

		verificaVencimento();

	}

	private void verificaVencimento() {
		LocalDate hoje = LocalDate.now();
		LocalDate vencimentoLocalDate = this.vencimento;

		this.estaVencida = vencimentoLocalDate.isBefore(hoje);
		this.venceHoje = vencimentoLocalDate.isEqual(hoje);
	}

	public void definirArquivos(Arquivo arquivo) {
		if (arquivo.eBoleto()) {
			this.idBoleto = arquivo.getId();
			this.nomeBoleto = arquivo.getNome();
		}
		if (arquivo.eComprovante()) {
			this.idComprovante = arquivo.getId();
			this.nomeComprovante = arquivo.getNome();
		}
	}
}
