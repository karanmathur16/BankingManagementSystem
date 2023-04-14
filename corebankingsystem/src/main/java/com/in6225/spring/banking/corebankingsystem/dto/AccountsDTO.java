package com.in6225.spring.banking.corebankingsystem.dto;

import java.math.BigDecimal;

import com.in6225.spring.banking.corebankingsystem.AccountStatus;
import com.in6225.spring.banking.corebankingsystem.AccountType;
import com.in6225.spring.banking.corebankingsystem.entities.Users;

import lombok.Data;

@Data
public class AccountsDTO {
	private long idaccounts;
	private String AccountNumber;
	private AccountType AccountType;
	private AccountStatus AccountStatus;
	private BigDecimal AvailableBalance;
	private Users user;	
	//private long userId;
	
}
