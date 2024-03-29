package com.example.demo.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Usuario implements UserDetails{
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		private Integer edad;
		@JoinColumn(name = "fk_instructor_id")
		private Integer instructorId;
		@JoinColumn(name = "fk_rutina_id")
		private Integer rutinaId;
		private String cedula;
		private String apellido;
		private String genero;
		private String nombre;
		private String correo;
		private String password;
		
		@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
		@JsonIgnore
		private Set<UsuarioRol> usuarioRoles = new HashSet<>();

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			Set<Authority> autoridades = new HashSet<>();
			this.usuarioRoles.forEach(usuarioRol ->{
				autoridades.add(new Authority(usuarioRol.getRol().getNombre()));
			});
			return autoridades;
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
		

}
