package com.augusto.business;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.augusto.model.Arquivo;
import com.augusto.model.ContaPagar;
import com.augusto.model.dto.ContaDTO;
import com.augusto.model.dto.ListagemDeContaDTO;
import com.augusto.model.dto.PesquisaContaDTO;
import com.augusto.model.dto.SalvarPagamentoDTO;
import com.augusto.model.enums.SituacaoConta;
import com.augusto.model.enums.TipoArquivo;
import com.augusto.repository.ContaRepository;
import com.augusto.repository.ContaRepositoryImpl;

@Component
public class ContaPagarBusiness {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ContaRepositoryImpl repositoryImpl;

	@Autowired
	private ArquivoBusiness arquivoBusiness;

	@Transactional(rollbackFor = Exception.class)
	public void salvarConta(ContaPagar contaBoletoBuild) throws Exception {

		ContaPagar contaPagarPersistida = this.contaRepository.save(contaBoletoBuild);
		contaPagarPersistida.getArquivo().forEach(arq -> {
			arq.setContaPagar(contaPagarPersistida);
			this.arquivoBusiness.salvar(arq);
		});
	}

	public Collection<ListagemDeContaDTO> obterContas(PesquisaContaDTO pesquisaContaDTO) {
		pesquisaContaDTO.descricaoCheckNonNull();
		pesquisaContaDTO.converterDateStringsToDate();
		pesquisaContaDTO.converterSituacaoParaEnumSituacao();
		Collection<ListagemDeContaDTO> contas = this.contaRepository.obterContas(pesquisaContaDTO);

		for (ListagemDeContaDTO listagemDeContaDTO : contas) {
			for (Arquivo arquivo : this.arquivoBusiness.getArquivos(listagemDeContaDTO.getIdConta())) {
					listagemDeContaDTO.definirArquivos(arquivo);
			}
		}

		return contas;
	}

	public void deletar(Long idConta) {
		this.contaRepository.deleteById(idConta);

	}

	@Transactional(rollbackFor = Exception.class)
	public void salvarContaPagamento(SalvarPagamentoDTO salvarPagamentoDTO) throws IOException {
		this.arquivoBusiness.salvar(salvarPagamentoDTO.obterArquivoComprovante());
		this.contaRepository.atualizarContaPagar(SituacaoConta.PAGO,
				salvarPagamentoDTO.getIdContaSaida(), salvarPagamentoDTO.getComentarioDePagamento(), salvarPagamentoDTO.getIdContaPagar());
	}

}
