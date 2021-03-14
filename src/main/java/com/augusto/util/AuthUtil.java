package com.augusto.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.augusto.model.Usuario;

public class AuthUtil {

	public static Usuario obterUsuario() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
