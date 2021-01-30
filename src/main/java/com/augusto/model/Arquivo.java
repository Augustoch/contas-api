package com.augusto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.augusto.model.enums.TipoArquivo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Arquivo {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique = true)
	private String nome;
	
	@Lob
	@Column()
	private byte[] data;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoArquivo tipoArquivo;
	
	@ManyToOne
	private ContaPagar contaPagar;
	
	public Arquivo(Long id, String nome, TipoArquivo tipoArquivo) {
		this.id = id;
		this.nome = nome;
		this.tipoArquivo = tipoArquivo;
	}
	
	
	public boolean eBoleto(){
		return this.tipoArquivo == TipoArquivo.BOLETO;
	}
	
	public boolean eComprovante(){
		return this.tipoArquivo == TipoArquivo.COMPROVANTE;
	}
}
