package com.in6225.spring.banking.corebankingsystem.controller.response;

import lombok.Data;

@Data
public class FundTransferResponse {

	private String message;
	private String transactionId;
	
	public FundTransferResponse(String message, String transactionId) {
		super();
		this.message = message;
		this.transactionId = transactionId;
	}
	
	

}
