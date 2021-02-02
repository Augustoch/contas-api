package com.augusto.conf.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.augusto.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenLocalService {
	
	
	  @Value("${conta.jwt.expiration}") private String expiration;
	  
	  @Value("${conta.jwt.secret}") private String secret;
	 
	
	public String gerarToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		
		  Date hoje = new Date(); Date dateExpiration = new Date(hoje.getTime() +
		  Long.parseLong(expiration));
		 
		return Jwts.builder()
				.setIssuer("API Contas")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}


	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public Long getIdUsuario(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());
	}

}
