package com.augusto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.business.DadoContaBancariaBusiness;
import com.augusto.model.DadoContaBancaria;

@RestController
@RequestMapping("contas-banco")
public class DadoContaBancariaController {
	
	@Autowired
	private DadoContaBancariaBusiness business;
	
	@PostMapping
	public void salvar(@RequestBody String descricao) {
		this.business.salvar(DadoContaBancaria.builder().descricao(descricao).build());
	}
	
	@GetMapping
	public Collection<DadoContaBancaria> obter(){
		return this.business.obter();
	}
	
	@DeleteMapping("{id}")
	public void deletar(@PathVariable Long id) {
		this.business.deletar(id);
	}
}
