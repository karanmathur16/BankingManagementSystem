package com.in6225.spring.banking.corebankingsystem.controller.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DepositRequest {
	
	private String accountNumber;
	private BigDecimal amount;
	private String userId;

}
