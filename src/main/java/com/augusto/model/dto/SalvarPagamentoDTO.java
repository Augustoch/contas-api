package com.augusto.model.dto;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.augusto.model.Arquivo;
import com.augusto.model.ContaPagar;
import com.augusto.model.DadoContaBancaria;
import com.augusto.model.enums.SituacaoConta;
import com.augusto.model.enums.TipoArquivo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SalvarPagamentoDTO {
	private Long idContaPagar;
	private Long idContaSaida;
	private String comentarioDePagamento;
	private MultipartFile comprovante;
	private Long idEmpresaPagamento;
	
	private SituacaoConta situacaoConta;

	public Arquivo obterArquivoComprovante() throws IOException {
		return Arquivo.builder().nome(comprovante.getOriginalFilename()).data(comprovante.getBytes())
				.contaPagar(obterContaPagar()).tipoArquivo(TipoArquivo.COMPROVANTE).build();
	}

	public ContaPagar obterContaPagar() {
		return ContaPagar.builder().id(idContaPagar).contaDeSaida(DadoContaBancaria.builder().id(idContaSaida).build())
				.comentarioDePagamento(comentarioDePagamento).build();
	}

}
