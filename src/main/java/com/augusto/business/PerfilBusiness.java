package com.augusto.business;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.augusto.model.Perfil;
import com.augusto.repository.PerfilRepository;

@Component
public class PerfilBusiness {

	
	@Autowired
	private PerfilRepository repository;
	
	public Collection<Perfil> obterPerfis() {
		return this.repository.findAll();
	}
	
}
