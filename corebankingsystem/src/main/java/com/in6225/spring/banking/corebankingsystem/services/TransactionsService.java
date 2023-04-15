package com.in6225.spring.banking.corebankingsystem.services;

import java.math.BigDecimal;
import java.util.List;

import com.in6225.spring.banking.corebankingsystem.controller.request.FundsTransferRequest;
import com.in6225.spring.banking.corebankingsystem.controller.response.FundTransferResponse;
import com.in6225.spring.banking.corebankingsystem.dto.AccountsDTO;
import com.in6225.spring.banking.corebankingsystem.dto.TransactionsDTO;

public interface TransactionsService {
	FundTransferResponse fundsTransfer(FundsTransferRequest fundsTransferRequest);
	List<TransactionsDTO> getTransaction(String transactionid);
	List<TransactionsDTO> findall(AccountsDTO account);
	TransactionsDTO deposit(String accountNumber, BigDecimal amount);
	TransactionsDTO withdraw(String accountNumber, BigDecimal amount);
}
