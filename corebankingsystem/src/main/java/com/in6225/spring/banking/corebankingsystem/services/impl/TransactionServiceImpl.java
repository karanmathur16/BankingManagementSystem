package com.in6225.spring.banking.corebankingsystem.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in6225.spring.banking.corebankingsystem.TransactionType;
import com.in6225.spring.banking.corebankingsystem.controller.request.FundsTransferRequest;
import com.in6225.spring.banking.corebankingsystem.controller.response.FundTransferResponse;
import com.in6225.spring.banking.corebankingsystem.dto.AccountsDTO;
import com.in6225.spring.banking.corebankingsystem.dto.TransactionsDTO;
import com.in6225.spring.banking.corebankingsystem.entities.Accounts;
import com.in6225.spring.banking.corebankingsystem.entities.Transactions;
import com.in6225.spring.banking.corebankingsystem.mapper.AccountsMapping;
import com.in6225.spring.banking.corebankingsystem.mapper.TransactionsMapping;
import com.in6225.spring.banking.corebankingsystem.repos.AccountsRepository;
import com.in6225.spring.banking.corebankingsystem.repos.TransactionsRepository;
import com.in6225.spring.banking.corebankingsystem.services.TransactionsService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionsService {
	
	@Autowired
	private AccountsServiceImpl accountService;
	@Autowired
    private TransactionsRepository transactionrepo;
	@Autowired
	private AccountsRepository accountsrepo;	
	
	private TransactionsMapping tranmapper = new TransactionsMapping();
	private AccountsMapping accountmapper = new AccountsMapping();

	@Override
	public FundTransferResponse fundsTransfer(FundsTransferRequest fundsTransferRequest) {
		
		AccountsDTO fromBankAccount = accountService.getAccount(fundsTransferRequest.getFromAccount());
        AccountsDTO toBankAccount = accountService.getAccount(fundsTransferRequest.getToAccount());

        validateBalance(fromBankAccount, fundsTransferRequest.getAmount());

        String transactionId = UUID.randomUUID().toString();
        
        BigDecimal amount = fundsTransferRequest.getAmount();

        Accounts fromBankAccountEntity = accountsrepo.findByAccountNumber(fromBankAccount.getAccountNumber()).get();
        Accounts toBankAccountEntity = accountsrepo.findByAccountNumber(toBankAccount.getAccountNumber()).get();

        fromBankAccountEntity.setAvailableBalance(fromBankAccountEntity.getAvailableBalance().subtract(amount));
        accountsrepo.save(fromBankAccountEntity);

        Transactions tran1 = new Transactions();
        tran1.setAccount(fromBankAccountEntity);
        tran1.setAmount(amount.negate());
        tran1.setTransactionDateTime(LocalDateTime.now());
        tran1.setTransactionNumber(transactionId);
        tran1.setTransactionType(TransactionType.FUND_TRANSFER);
        transactionrepo.save(tran1);

        toBankAccountEntity.setAvailableBalance(toBankAccountEntity.getAvailableBalance().add(amount));
        
        accountsrepo.save(toBankAccountEntity);
        
        Transactions tran2 = new Transactions();
        tran2.setAccount(toBankAccountEntity);
        tran2.setAmount(amount);
        tran2.setTransactionDateTime(LocalDateTime.now());
        tran2.setTransactionNumber(transactionId);
        tran2.setTransactionType(TransactionType.FUND_TRANSFER);
        transactionrepo.save(tran2);

        FundTransferResponse ftr = new FundTransferResponse("Transaction successfully completed",transactionId);
        
        return ftr;

	}

	@Override
	public List<TransactionsDTO> getTransaction(String transactionid) {
		List<Transactions> trans = transactionrepo.findBytransactionNumber(transactionid).get();
		return (tranmapper.convertToDtoList(trans));
	}

	@Override
	public List<TransactionsDTO> findall(AccountsDTO account) {
		Accounts accountentity = accountmapper.convertToEntity(account);
		List<Transactions> transactionsList = transactionrepo.findByAccount(accountentity).get();
		return(tranmapper.convertToDtoList(transactionsList));
	}
	
	@Override
	public TransactionsDTO deposit(String accountNumber, BigDecimal amount) {
		Accounts accountentity = accountsrepo.findByAccountNumber(accountNumber).get();
		accountentity.setAvailableBalance(accountentity.getAvailableBalance().add(amount));
		Transactions tran1 = new Transactions();
		String transactionId = UUID.randomUUID().toString();
        tran1.setAccount(accountentity);
        tran1.setAmount(amount);
        tran1.setTransactionDateTime(LocalDateTime.now());
        tran1.setTransactionNumber(transactionId);
        tran1.setTransactionType(TransactionType.CREDIT);
        transactionrepo.save(tran1);
		return (tranmapper.convertToDto(tran1));
	}

	@Override
	public TransactionsDTO withdraw(String accountNumber, BigDecimal amount) {
		Accounts accountentity = accountsrepo.findByAccountNumber(accountNumber).get();
		AccountsDTO accountdto = accountmapper.convertToDto(accountentity);
		
		validateBalance(accountdto, amount);
		
		accountentity.setAvailableBalance(accountentity.getAvailableBalance().subtract(amount));
		Transactions tran1 = new Transactions();
		String transactionId = UUID.randomUUID().toString();
        tran1.setAccount(accountentity);
        tran1.setAmount(amount.negate());
        tran1.setTransactionDateTime(LocalDateTime.now());
        tran1.setTransactionNumber(transactionId);
        tran1.setTransactionType(TransactionType.DEBIT);
        transactionrepo.save(tran1);
		return (tranmapper.convertToDto(tran1));
	}

	private void validateBalance(AccountsDTO bankAccount, BigDecimal amount) {
        if (bankAccount.getAvailableBalance().compareTo(BigDecimal.ZERO) < 0 || bankAccount.getAvailableBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Not enough funds to transfer");
        }
    }

}
