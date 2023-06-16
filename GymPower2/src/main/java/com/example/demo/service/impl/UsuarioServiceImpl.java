package com.example.demo.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.entity.UsuarioRol;
import com.example.demo.repository.RolRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	
	public List<Usuario> findAllUsuarios() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario saveUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
		// TODO Auto-generated method stub
		Usuario usuariolocal = usuarioRepository.findByCorreo(usuario.getCorreo());
		if(usuariolocal != null) {
			System.out.println("EL usuario ya existe");
			throw new Exception("El usuario ya esta presente");
		}
		else {
			for(UsuarioRol usuarioRol:usuarioRoles) {
				
				rolRepository.save(usuarioRol.getRol());
			}
			usuario.getUsuarioRoles().addAll(usuarioRoles);
			usuariolocal = usuarioRepository.save(usuario);
		}
		
		return usuariolocal;
	}

	@Override
	public Usuario obtenerUsuario(String correo) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByCorreo(correo);
	}

	@Override
	public void deleteUsuario(Integer id) {
		// TODO Auto-generated method stub
		usuarioRepository.deleteById(id);;
	}
	}

