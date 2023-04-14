package com.in6225.spring.banking.corebankingsystem.services;

import java.math.BigDecimal;
import java.util.List;

import com.in6225.spring.banking.corebankingsystem.dto.AccountStatementDTO;
import com.in6225.spring.banking.corebankingsystem.dto.AccountsDTO;
import com.in6225.spring.banking.corebankingsystem.dto.UsersDTO;

public interface AccountsService {
	List<AccountsDTO> findAll();
	AccountsDTO save(AccountsDTO account);
    AccountStatementDTO getStatement(AccountsDTO account);
    AccountsDTO getAccount(String AccountNumber);
    List<AccountsDTO> findall(UsersDTO user);
    BigDecimal getBalance(String AccountNumber);
}
