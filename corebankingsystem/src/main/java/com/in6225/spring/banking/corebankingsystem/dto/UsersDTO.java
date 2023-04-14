package com.in6225.spring.banking.corebankingsystem.dto;

import com.in6225.spring.banking.corebankingsystem.UserType;

import lombok.Data;

@Data
public class UsersDTO {
	private long idusers;
	private String username;
	//private String password;
	private String email;
	private UserType type;
//	private List<AccountsDTO> accounts;
		
	
}
