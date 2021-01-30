package com.augusto.business;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.augusto.model.Arquivo;
import com.augusto.repository.ArquivoRepository;

@Component
public class ArquivoBusiness {
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	public void salvar(Arquivo arq) {
		this.arquivoRepository.save(arq);		
	}

	public byte[] getArquivo(Long id) {
		return this.arquivoRepository.getData(id);
	}

	public Collection<Arquivo> getArquivos(Long idConta) {
		return this.arquivoRepository.getArquivos(idConta);
	}
	
}
