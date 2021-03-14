package com.augusto.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Historico {
	@Id
	private Long id;
	
	private String descricao;
}
