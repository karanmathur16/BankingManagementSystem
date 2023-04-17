package com.in6225.spring.banking.corebankingsystem.controller.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FundsTransferRequest {

	private String fromAccount;
	private String toAccount;
	private BigDecimal amount;
	private String userId;

}
