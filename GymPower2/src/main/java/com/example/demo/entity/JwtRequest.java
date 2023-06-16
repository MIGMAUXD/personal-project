	package com.example.demo.entity;

import lombok.Data;

@Data
public class JwtRequest {
	
	private String correo;
	private String password;
	
	public JwtRequest() {
		
	}

	public JwtRequest(String correo, String password) {
		this.correo = correo;
		this.password = password;
	}
	
	
	
	
}
