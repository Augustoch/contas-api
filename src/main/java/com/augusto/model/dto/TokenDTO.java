package com.augusto.model.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.augusto.model.Perfil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {

	private String token;
	private String tipo;
	private Collection<? extends GrantedAuthority> perfis;
	
	
	public TokenDTO(String token, String tipo, Collection<? extends GrantedAuthority> collection) {
		this.token = token;
		this.tipo = tipo;
		this.perfis = collection;
	}

}
