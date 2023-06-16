package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.entity.Usuario;
import com.example.demo.entity.UsuarioRol;

public interface UsuarioService {

		
		public List<Usuario> findAllUsuarios();
		
		public Usuario saveUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;
		
		public Usuario obtenerUsuario(String correo);
		
		public void deleteUsuario(Integer id);
}
