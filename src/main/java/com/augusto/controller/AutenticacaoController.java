package com.augusto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.conf.security.TokenLocalService;
import com.augusto.model.Usuario;
import com.augusto.model.dto.TokenDTO;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenLocalService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody Usuario usuario){
		UsernamePasswordAuthenticationToken dadosLogin = usuario.converter();
		
		try {
			Authentication authentication = authenticationManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer", ((Usuario) authentication.getPrincipal() ).getAuthorities()));			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
