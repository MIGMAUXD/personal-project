package com.example.demo.entity;

import lombok.Data;

@Data
public class JwtResponse {

	private String token;

	public JwtResponse(String token) {
		this.token = token;
	}
	
	
}
