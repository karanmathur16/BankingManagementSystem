package com.in6225.spring.banking.corebankingsystem.controller.request;

import java.math.BigDecimal;

import com.in6225.spring.banking.corebankingsystem.AccountStatus;
import com.in6225.spring.banking.corebankingsystem.AccountType;

import lombok.Data;

@Data
public class AccountCreationRequest {
	
	private long idaccounts;
	private String AccountNumber;
	private AccountType AccountType;
	private AccountStatus AccountStatus;
	private BigDecimal AvailableBalance;	
	private long userId;

}
