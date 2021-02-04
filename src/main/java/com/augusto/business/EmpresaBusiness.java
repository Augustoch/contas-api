package com.augusto.business;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.augusto.model.Empresa;
import com.augusto.repository.EmpresaRepository;

@Component
public class EmpresaBusiness {
	
	@Autowired
	private EmpresaRepository repository;
	
	public void salvar(Empresa empresa) {
		this.repository.save(empresa);		
	}

	public Collection<Empresa> obter() {
		return this.repository.findAll();
	}

	public void deletar(Long id) {
		this.repository.deleteById(id);		
	}
	
}
