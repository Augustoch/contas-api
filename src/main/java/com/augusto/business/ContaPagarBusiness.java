package com.augusto.business;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.augusto.model.Arquivo;
import com.augusto.model.ContaPagar;
import com.augusto.model.Usuario;
import com.augusto.model.dto.ListagemDeContaDTO;
import com.augusto.model.dto.PesquisaContaDTO;
import com.augusto.model.dto.SalvarPagamentoDTO;
import com.augusto.model.enums.SituacaoConta;
import com.augusto.repository.ContaRepository;
import com.augusto.repository.ContaRepositoryImpl;
import com.augusto.util.AuthUtil;

@Component
public class ContaPagarBusiness {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ContaRepositoryImpl repo;
	

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
		//Collection<ListagemDeContaDTO> contas = this.contaRepository.obterContas(pesquisaContaDTO);
		Collection<ListagemDeContaDTO> contas = this.repo.obterContas(pesquisaContaDTO);

		for (ListagemDeContaDTO listagemDeContaDTO : contas) {
			for (Arquivo arquivo : this.arquivoBusiness.getArquivos(listagemDeContaDTO.getIdConta())) {
					listagemDeContaDTO.definirArquivos(arquivo);
			}
		}

		return contas;
	}

	public Long deletar(Long idConta) {
		if(!AuthUtil.obterUsuario().eAdm()) {
			throw new RuntimeException("Sem permissão para exclusão");
		}
		this.contaRepository.deleteById(idConta);	
		return idConta;

	}

	@Transactional(rollbackFor = Exception.class)
	public void salvarContaPagamento(SalvarPagamentoDTO salvarPagamentoDTO) throws IOException {
		this.arquivoBusiness.salvar(salvarPagamentoDTO.obterArquivoComprovante());
		salvarPagamentoDTO.setSituacaoConta(SituacaoConta.PAGO);
		this.contaRepository.atualizarContaPagar(salvarPagamentoDTO);
	}


}
