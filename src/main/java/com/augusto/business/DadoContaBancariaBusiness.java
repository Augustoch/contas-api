package com.augusto.business;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
		try {
			this.repository.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("Não é possivel deletar esta conta banco pois ela está associada a uma conta a pagar");
		}
	}

}
