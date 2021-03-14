package com.augusto.model.dto;

import java.util.Collection;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.augusto.model.Perfil;
import com.augusto.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
	 	private String usuario;
		private String senha;
		private Collection<Perfil> perfis;
		public Usuario buildUsuario() {
			return Usuario.builder().usuario(usuario).senha(new BCryptPasswordEncoder().encode(senha)).perfis(perfis).build();
		}
}
