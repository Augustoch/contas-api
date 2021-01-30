package com.augusto.controller;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.augusto.business.ContaPagarBusiness;
import com.augusto.model.dto.ContaDTO;
import com.augusto.model.dto.ListagemDeContaDTO;
import com.augusto.model.dto.SalvarPagamentoDTO;

@RestController
@RequestMapping("contas-a-pagar")
public class ContaController {

	@Autowired
	private ContaPagarBusiness contaPagarBusiness;

	@PostMapping
	public void salvarConta(ContaDTO contaDTO) throws Exception {
		this.contaPagarBusiness.salvarConta(contaDTO.contaBoletoBuild());
	}

	@GetMapping
	public Collection<ListagemDeContaDTO> obterContas(ContaDTO contaDTO) {
		return this.contaPagarBusiness.obterContas(contaDTO);
	}

	@DeleteMapping("{idConta}")
	public void deletarConta(@PathVariable Long idConta) {
		this.contaPagarBusiness.deletar(idConta);
	}

	@PutMapping
	public void salvarPagamento(SalvarPagamentoDTO salvarPagamentoDTO) throws IOException {
		this.contaPagarBusiness.salvarContaPagamento(salvarPagamentoDTO);
	}
}
