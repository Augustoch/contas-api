package com.augusto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.business.PerfilBusiness;
import com.augusto.model.Perfil;

@RestController
@RequestMapping("perfis")
public class PerfilController {
	
	@Autowired
	private PerfilBusiness perfilBusiness;
	
	@GetMapping
	public Collection<Perfil> obterPerfis(){
		return this.perfilBusiness.obterPerfis();
	}
	
}
