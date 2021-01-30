package com.augusto.business;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.augusto.model.DadoContaBancaria;
import com.augusto.repository.DadoContaBancariaRepository;

@Component
public class DadoContaBancariaBusiness {

	@Autowired
	private DadoContaBancariaRepository repository;

	public void salvar(DadoContaBancaria dadoContaBancaria) {
		this.repository.save(dadoContaBancaria);
	}

	public Collection<DadoContaBancaria> obter() {
		return this.repository.findAll();
	}

	public void deletar(Long id) {
		this.repository.deleteById(id);
	}

}
