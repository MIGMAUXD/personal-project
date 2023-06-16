	package com.example.demo.entity;

import lombok.Data;

@Data
public class JwtRequest {
	
	private String codigo;
	private String password;
	
	public JwtRequest() {
		
	}

	public JwtRequest(String codigo, String password) {
		this.codigo = codigo;
		this.password = password;
	}
	
	
	
	
}
