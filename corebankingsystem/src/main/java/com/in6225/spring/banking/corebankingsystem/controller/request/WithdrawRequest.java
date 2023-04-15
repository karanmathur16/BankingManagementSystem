package com.in6225.spring.banking.corebankingsystem.controller.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class WithdrawRequest {
	
	private String accountNumber;
	private BigDecimal amount;

}
