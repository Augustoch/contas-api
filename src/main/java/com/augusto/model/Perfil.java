package com.augusto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Perfil implements GrantedAuthority {
	
	@Id @GeneratedValue
	private Long id;
	private String nome;
	
	@Override
	public String getAuthority() {
		return this.nome;
	}

}
