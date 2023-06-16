package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
		public Usuario findByCodigo(String codigo);
		public Usuario findByDocumento(Integer documento);
}
