package com.example.demo.controller;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.UsuarioRol;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin( {"*"})
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/list")
	public ResponseEntity<?> findAllUsuarios() {
		try {
			return ResponseEntity.ok(usuarioService.findAllUsuarios());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
	
	@PostMapping("/save")
	public Usuario saveUsuario(@RequestBody Usuario usuario) throws Exception{
		
		Set<UsuarioRol> usuarioRoles = new HashSet<>();
		
		Rol rol = new Rol();
		rol.setId(3);
		rol. setNombre ("ADMIN");
		
		
		UsuarioRol usuarioRol = new UsuarioRol ();
		usuarioRol.setUsuario(usuario);
		usuarioRol.setRol(rol);
		usuarioRoles.add(usuarioRol);
		
		return usuarioService.saveUsuario(usuario, usuarioRoles);
		
	}
	
	@GetMapping("/{codigo}")
	public Usuario obtenerUsuario(@PathVariable("codigo") String codigo) {
		return usuarioService.obtenerUsuario(codigo);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteUsuario(@PathVariable("id") Integer id){
		usuarioService.deleteUsuario(id);
	}
	
}
