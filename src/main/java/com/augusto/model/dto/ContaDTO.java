package com.augusto.model.dto;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.augusto.model.Arquivo;
import com.augusto.model.ContaPagar;
import com.augusto.model.Empresa;
import com.augusto.model.Usuario;
import com.augusto.model.enums.SituacaoConta;
import com.augusto.model.enums.TipoArquivo;
import com.augusto.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {
	private Long id;
	private String descricao;
	private String comentarios;
	private MultipartFile boleto;
	private LocalDate vencimento;
	private Long idEmpresaResposavel;
	
	public void setVencimento(String vencimento) {
		this.vencimento = LocalDate.parse(vencimento);
	}
	
	public ContaPagar contaBoletoBuild() throws IOException, ParseException {
		return ContaPagar.builder().id(getId()).descricao(getDescricao()).comentarios(getComentarios())
				.arquivo(buildArquivo()).vencimento(DateUtil.locaDateToDate(vencimento))
						.situacaoConta(SituacaoConta.AGUARDANDO_PAGAMENTO)
						.empresaReponsavelConta(Empresa.builder().id(idEmpresaResposavel).build())
				.criacao(DateUtil.locaDateToDate(LocalDate.now()))
				.criador((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.build();
	}


	private List<Arquivo> buildArquivo() throws IOException {
		if(Objects.isNull(boleto)) {
			return  Arrays.asList();
		}
		return Arrays.asList(Arquivo.builder().nome(boleto.getOriginalFilename()).data(boleto.getBytes())
				.tipoArquivo(TipoArquivo.BOLETO).build());
	}
	
	
//	private Date vencimentoStringToDate() throws ParseException {
//		return new SimpleDateFormat("yyyy-MM-dd").parse(vencimento);
//	}
}
