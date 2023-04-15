package com.in6225.spring.banking.corebankingsystem.dto;

import java.math.BigDecimal;
import java.util.List;

import com.in6225.spring.banking.corebankingsystem.TransactionType;

import lombok.Data;

@Data
public class AccountStatementDTO {
	
	BigDecimal currentBalance;
    List<TransactionsDTO> transactionHistory;
    String accountNumber;
    
}
