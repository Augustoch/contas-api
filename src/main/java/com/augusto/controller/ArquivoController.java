package com.augusto.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.augusto.business.ArquivoBusiness;

@RestController
@RequestMapping("arquivos")
public class ArquivoController {

	@Autowired
	private ArquivoBusiness arquivoBusiness;

	@GetMapping("{id}")
	public byte[] getArquivo(@PathVariable Long id) {

		return this.arquivoBusiness.getArquivo(id);
	}

	@GetMapping("boleto-comprovante")
	public byte[] boletoComprovante(@RequestParam Long idBoleto, @RequestParam Long idComprovante) throws IOException {
		return this.arquivoBusiness.boletoComprovante(idBoleto, idComprovante);

	}
	
	@PutMapping
	public void atualizar(Long idArquivo,  MultipartFile file) throws IOException {
		this.arquivoBusiness.atualizar(idArquivo, file.getOriginalFilename(), file.getBytes());
	}

}
