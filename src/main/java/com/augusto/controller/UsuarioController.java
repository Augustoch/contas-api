package com.augusto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.model.dto.UsuarioDTO;
import com.augusto.repository.UsuarioRepository;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public void salvarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		try {
			this.usuarioRepository.save(usuarioDTO.buildUsuario());			
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("Já existe usuário com esse nome");
		}
	}
	
}
