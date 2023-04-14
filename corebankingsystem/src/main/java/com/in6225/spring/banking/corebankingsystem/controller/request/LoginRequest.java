package com.in6225.spring.banking.corebankingsystem.controller.request;

import lombok.Data;

@Data
public class LoginRequest {
	private String username;
	private String password;
}
