package com.in6225.spring.banking.corebankingsystem.service.impl.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.in6225.spring.banking.corebankingsystem.AccountStatus;
import com.in6225.spring.banking.corebankingsystem.AccountType;
import com.in6225.spring.banking.corebankingsystem.dto.AccountsDTO;
import com.in6225.spring.banking.corebankingsystem.entities.Users;
import com.in6225.spring.banking.corebankingsystem.mapper.UserMapping;
import com.in6225.spring.banking.corebankingsystem.services.impl.AccountsServiceImpl;
import com.in6225.spring.banking.corebankingsystem.services.impl.UserServiceImpl;

@SpringBootTest
public class AccountsServiceTest {
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	UserServiceImpl userservice;

	@Test
	void contextLoads() {
		AccountsServiceImpl accountservice =context.getBean(AccountsServiceImpl.class);
		UserMapping usermapper = new UserMapping();
		
		AccountsDTO account1 = new AccountsDTO();
		account1.setAccountNumber("12969161511111");
		account1.setAccountStatus(AccountStatus.ACTIVE);
		account1.setAccountType(AccountType.SAVINGS);
		account1.setAvailableBalance(BigDecimal.valueOf(100.12));
		Users user =usermapper.convertToEntity(userservice.getUser(6L));
		account1.setUser(user);
		
		accountservice.save(account1);
	}

}
