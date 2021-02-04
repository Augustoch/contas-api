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

import com.augusto.business.EmpresaBusiness;
import com.augusto.model.Empresa;

@RestController
@RequestMapping("empresas")
public class EmpresaController {
	
	@Autowired
	private EmpresaBusiness business ;
	
	@PostMapping
	public void salvar(@RequestBody String descricao) {
		this.business.salvar(Empresa.builder().descricao(descricao).build());
	}
	
	@GetMapping
	public Collection<Empresa> obter(){
		return this.business.obter();
	}
	
	@DeleteMapping("{id}")
	public void deletar(@PathVariable Long id) {
		this.business.deletar(id);
	}
}
