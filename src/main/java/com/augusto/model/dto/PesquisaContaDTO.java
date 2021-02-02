package com.augusto.model.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.augusto.model.enums.SituacaoConta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PesquisaContaDTO {
	private Long id;
	private String descricao;
	private String vencInicial;
	private String vencFinal;
	private String situacao;
	private Long idContaSaida;
	
	private Date vencInicialDate;
	private Date vencFinalDate;
	
	private SituacaoConta situacaoConta;
	
	
	private Date obterVencInicialAsDate() throws ParseException {
		if(Objects.nonNull(vencInicial)) {
			return new SimpleDateFormat("yyyy-MM-dd").parse(vencInicial);			
		}
		return null;
	}
	private Date obterVencFInalAsDate() throws ParseException {
		if(Objects.nonNull(vencFinal)) {
			return new SimpleDateFormat("yyyy-MM-dd").parse(vencFinal);
		}
		return null;
	}
	
	public void converterDateStringsToDate() {
				
		try {
			this.vencInicialDate = this.obterVencInicialAsDate();
			this.vencFinalDate = this.obterVencFInalAsDate();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void converterSituacaoParaEnumSituacao() {
		if(Objects.nonNull(situacao)) {
			this.situacaoConta = SituacaoConta.valueOf(situacao);			
		}
	}
	public void descricaoCheckNonNull() {
		if(Objects.isNull(descricao)) {
			this.descricao = "";
		}		
	}
	
	
	
}
