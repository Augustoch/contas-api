package com.augusto.model.dto;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.augusto.model.Arquivo;
import com.augusto.model.ContaPagar;
import com.augusto.model.Empresa;
import com.augusto.model.enums.SituacaoConta;
import com.augusto.model.enums.TipoArquivo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {
	private Long id;
	private String descricao;
	private String comentarios;
	private MultipartFile boleto;
	private String vencimento;
	private Long idEmpresaResposavel;

	public ContaPagar contaBoletoBuild() throws IOException, ParseException {
		return ContaPagar.builder().id(getId()).descricao(getDescricao()).comentarios(getComentarios())
				.arquivo(Arrays.asList(Arquivo.builder().nome(boleto.getOriginalFilename()).data(boleto.getBytes())
						.tipoArquivo(TipoArquivo.BOLETO).build())).vencimento(vencimentoStringToDate())
						.situacaoConta(SituacaoConta.AGUARDANDO_PAGAMENTO)
						.empresaReponsavelConta(Empresa.builder().id(idEmpresaResposavel).build())
				.build();
	}
	
	
	private Date vencimentoStringToDate() throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(vencimento);
	}
}
