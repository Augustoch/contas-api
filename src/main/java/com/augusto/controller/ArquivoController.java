package com.augusto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.business.ArquivoBusiness;

@RestController
@RequestMapping("arquivos")
public class ArquivoController {
	
	@Autowired
	private ArquivoBusiness arquivoBusiness;
	
	@GetMapping("{id}")
	public byte[] getArquivo(@PathVariable Long id){
		
		return this.arquivoBusiness.getArquivo(id);
	}
	
}
