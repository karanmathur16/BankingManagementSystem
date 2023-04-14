package com.in6225.spring.banking.corebankingsystem.services.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in6225.spring.banking.corebankingsystem.dto.AccountStatementDTO;
import com.in6225.spring.banking.corebankingsystem.dto.AccountsDTO;
import com.in6225.spring.banking.corebankingsystem.dto.TransactionsDTO;
import com.in6225.spring.banking.corebankingsystem.dto.UsersDTO;
import com.in6225.spring.banking.corebankingsystem.entities.Accounts;
import com.in6225.spring.banking.corebankingsystem.entities.Transactions;
import com.in6225.spring.banking.corebankingsystem.entities.Users;
import com.in6225.spring.banking.corebankingsystem.mapper.AccountsMapping;
import com.in6225.spring.banking.corebankingsystem.mapper.TransactionsMapping;
import com.in6225.spring.banking.corebankingsystem.mapper.UserMapping;
import com.in6225.spring.banking.corebankingsystem.repos.AccountsRepository;
import com.in6225.spring.banking.corebankingsystem.repos.TransactionsRepository;
import com.in6225.spring.banking.corebankingsystem.services.AccountsService;

@Service
public class AccountsServiceImpl implements AccountsService {
	
	private AccountsMapping accountmapper = new AccountsMapping();
	private UserMapping usermapper = new UserMapping();
	private TransactionsMapping tranmapper = new TransactionsMapping();
	
	@Autowired
	private AccountsRepository accountrepo;
	
	@Autowired
	private TransactionsRepository transactionrepo;

	@Override
	public List<AccountsDTO> findAll() {
		return(accountmapper.convertToDtoList(accountrepo.findAll()));
	}

	@Override
	public AccountsDTO save(AccountsDTO account) {
		Accounts accountEntity = accountmapper.convertToEntity(account);
		accountrepo.save(accountEntity);
		return (accountmapper.convertToDto(accountEntity));
	}

	@Override
	public AccountStatementDTO getStatement(AccountsDTO account) {
		AccountStatementDTO asdto = new AccountStatementDTO();
		Accounts accountEntity = accountmapper.convertToEntity(account);
		Optional<List<Transactions>> optionalTransactions = transactionrepo.findByAccount(accountEntity);
		List<Transactions> transactionsList = optionalTransactions
	            .orElse(Collections.emptyList());
		List<TransactionsDTO> transactionsDTOlist = tranmapper.convertToDtoList(transactionsList);
		asdto.setTransactionHistory(transactionsDTOlist);
		asdto.setCurrentBalance(accountEntity.getAvailableBalance());
		asdto.setAccountNumber(accountEntity.getAccountNumber());
		return asdto;
	}

	@Override
	public AccountsDTO getAccount(String AccountNumber) {
		Optional<Accounts> account = accountrepo.findByAccountNumber(AccountNumber);
		Accounts accounts = account.orElse(null);
		return (accountmapper.convertToDto(accounts));
	}

	@Override
	public List<AccountsDTO> findall(UsersDTO user) {
		Users userentity = usermapper.convertToEntity(user);
		 List<Accounts> accounts = accountrepo.findByUser(userentity)
		            .orElse(Collections.emptyList());	            
		return (accountmapper.convertToDtoList(accounts));
	}

	@Override
	public BigDecimal getBalance(String AccountNumber) {
		Optional<Accounts> optionalaccount = accountrepo.findByAccountNumber(AccountNumber);
		Accounts account = optionalaccount.orElse(null);
		return (account.getAvailableBalance());
	}

}
