package com.in6225.spring.banking.corebankingsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.in6225.spring.banking.corebankingsystem.TransactionType;

import lombok.Data;

@Data
public class TransactionsDTO {
	
	//private Long idtransaction;
    private BigDecimal amount;
    private String transactionNumber;
    //private Accounts account;
    TransactionType transactionType;
    private LocalDateTime transactionDateTime;
    
}
