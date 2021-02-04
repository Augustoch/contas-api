package com.augusto.business;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
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

	public byte[] boletoComprovante(Long idBoleto, Long idComprovante) throws IOException {
		byte[] boleto = this.getArquivo(idBoleto);
		byte[] comprovante = this.getArquivo(idComprovante);

		ByteArrayOutputStream pdfSaida = new ByteArrayOutputStream();
		PDFMergerUtility merger = new PDFMergerUtility();
		merger.addSource(new ByteArrayInputStream(boleto));
		merger.addSource(new ByteArrayInputStream(comprovante));
		merger.setDestinationStream(pdfSaida);
		merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

		return pdfSaida.toByteArray();

	}

}
