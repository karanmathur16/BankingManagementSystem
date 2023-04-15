package com.in6225.spring.banking.corebankingsystem.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in6225.spring.banking.corebankingsystem.controller.request.DepositRequest;
import com.in6225.spring.banking.corebankingsystem.controller.request.FundsTransferRequest;
import com.in6225.spring.banking.corebankingsystem.controller.request.WithdrawRequest;
import com.in6225.spring.banking.corebankingsystem.dto.AccountsDTO;
import com.in6225.spring.banking.corebankingsystem.services.impl.AccountsServiceImpl;
import com.in6225.spring.banking.corebankingsystem.services.impl.TransactionServiceImpl;

@RestController
@RequestMapping(value = "/api/v1/transactions")
public class TransactionsController {
	
	@Autowired
	TransactionServiceImpl transervice;
	
	@Autowired
	AccountsServiceImpl accountservice;
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/fund-transfer")
	public ResponseEntity fundTransfer(@RequestBody FundsTransferRequest fundrequest){
        return ResponseEntity.ok(transervice.fundsTransfer(fundrequest));
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/{id}")
    public ResponseEntity getUser(@PathVariable("id") String idstr) {
        return ResponseEntity.ok(transervice.getTransaction(idstr));
    }
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/account/{accountNumber}")
    public ResponseEntity getUsers(@PathVariable("accountNumber") String accountNumber) {
		AccountsDTO adto = accountservice.getAccount(accountNumber);
        return ResponseEntity.ok(transervice.findall(adto));
    }
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/deposit")
	public ResponseEntity deposit(@RequestBody DepositRequest request){
		String accountNumber = request.getAccountNumber();
		BigDecimal amount = request.getAmount();
        return ResponseEntity.ok(transervice.deposit(accountNumber,amount));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/withdraw")
	public ResponseEntity withdraw(@RequestBody WithdrawRequest request){
		String accountNumber = request.getAccountNumber();
		BigDecimal amount = request.getAmount();
        return ResponseEntity.ok(transervice.withdraw(accountNumber,amount));
	}

}
