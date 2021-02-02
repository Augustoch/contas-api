package com.augusto.conf.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.augusto.model.Usuario;
import com.augusto.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	
	private TokenLocalService tokenLocalService;
	private UsuarioRepository usuarioRepository;
	
	
	public AutenticacaoViaTokenFilter(TokenLocalService tokenLocalService, UsuarioRepository usuarioRepository) {
		this.tokenLocalService = tokenLocalService;
		this.usuarioRepository = usuarioRepository;		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		boolean tokenValido = tokenLocalService.isTokenValido(token);
		
		if(tokenValido) {
			autenticarClient(token);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private void autenticarClient(String token) {
		Long idUsuario = tokenLocalService.getIdUsuario(token);
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
		Usuario usuario = usuarioOpt.get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario,null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);		
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
