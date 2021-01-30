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
import com.augusto.model.dto.SalvarPagamentoDTO;
import com.augusto.model.enums.SituacaoConta;
import com.augusto.model.enums.TipoArquivo;
import com.augusto.repository.ContaRepository;

@Component
public class ContaPagarBusiness {

	@Autowired
	private ContaRepository contaRepository;

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

	public Collection<ListagemDeContaDTO> obterContas(ContaDTO contaDTO) {
		Collection<ListagemDeContaDTO> contas = this.contaRepository.obterContas(contaDTO);

		for (ListagemDeContaDTO listagemDeContaDTO : contas) {
			for (Arquivo arquivo : this.arquivoBusiness.getArquivos(listagemDeContaDTO.getIdConta())) {
				if (arquivo.eBoleto()) {
					listagemDeContaDTO.setIdBoleto(arquivo.getId());
					listagemDeContaDTO.setNomeBoleto(arquivo.getNome());
				}
				if (arquivo.eComprovante()) {
					listagemDeContaDTO.setIdComprovante(arquivo.getId());
					listagemDeContaDTO.setNomeComprovante(arquivo.getNome());
				}
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
