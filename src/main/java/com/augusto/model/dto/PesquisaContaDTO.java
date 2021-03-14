package com.augusto.model.dto;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Convert;
import com.augusto.model.enums.SituacaoConta;
import com.augusto.util.DateUtil;
import com.augusto.util.converters.LocalDateToDateConverter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PesquisaContaDTO {
	private Long id;
	private String descricao = "";
	private Date vencInicial;
	private Date vencFinal;
	private Date pagInicial;
	private Date pagFinal;
	private SituacaoConta situacaoConta;
	private Long idContaSaida;

//	public PesquisaContaDTO() throws ParseException {
//		this.setVencFinal(null);
//		this.setVencFinal(null);
//	}
//
	public void setVencInicial(String date) throws ParseException  {
		
		this.vencInicial = DateUtil.parseDate(date);
		
	}
////
	public void setVencFinal(String date) throws ParseException {
		this.vencFinal = DateUtil.parseDate(date);
	}
//	public void setPagInicial(String date) throws ParseException {
//		this.pagInicial = LocalDate.parse(date);
//	}
//	
//	public void setPagFinal(String date) throws ParseException {
//		this.pagFinal = LocalDate.parse(date);
//	}
//	
	public void setSituacaoConta(String situacao) {
		if (Objects.nonNull(situacao)) {
			this.situacaoConta = SituacaoConta.valueOf(situacao);
		}
	}
	
	public boolean temDataPagamento(){
		return Objects.nonNull(pagInicial) && Objects.nonNull(pagFinal);
	}

}
