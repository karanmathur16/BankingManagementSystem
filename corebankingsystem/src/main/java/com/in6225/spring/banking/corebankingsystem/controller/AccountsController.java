package com.in6225.spring.banking.corebankingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in6225.spring.banking.corebankingsystem.controller.request.AccountCreationRequest;
import com.in6225.spring.banking.corebankingsystem.dto.AccountsDTO;
import com.in6225.spring.banking.corebankingsystem.dto.UsersDTO;
import com.in6225.spring.banking.corebankingsystem.entities.Users;
import com.in6225.spring.banking.corebankingsystem.mapper.UserMapping;
import com.in6225.spring.banking.corebankingsystem.services.impl.AccountsServiceImpl;
import com.in6225.spring.banking.corebankingsystem.services.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountsController {
	
	@Autowired
	private AccountsServiceImpl accountservice;
	
	@Autowired
	private UserServiceImpl userservice;
	
	private UserMapping usermapper = new UserMapping();
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/{accountNumber}")
    public ResponseEntity getAccount(@PathVariable("accountNumber") String accountNumber) {
        return ResponseEntity.ok(accountservice.getAccount(accountNumber));
    }
	
	@SuppressWarnings("rawtypes")
	@GetMapping()
    public ResponseEntity getAllAccounts() {
        return ResponseEntity.ok(accountservice.findAll());
    }
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/user/{id}")
    public ResponseEntity getAllAccounts(@PathVariable("id") String idstr) {
		Long id = Long.parseLong(idstr);
		UsersDTO userdto = userservice.getUser(id);
        return ResponseEntity.ok(accountservice.findall(userdto));
    }
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/statement/{accountNumber}")
    public ResponseEntity getStatement(@PathVariable("accountNumber") String accountNumber) {
		AccountsDTO accountdto = accountservice.getAccount(accountNumber);
        return ResponseEntity.ok(accountservice.getStatement(accountdto));
    }
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/create")
	public ResponseEntity createAccount(@RequestBody AccountCreationRequest accountrequest){
		AccountsDTO adto = new AccountsDTO();
		adto.setAccountNumber(accountrequest.getAccountNumber());
		System.out.println("accountNumber: " + accountrequest.getAccountNumber());
		adto.setAccountStatus(accountrequest.getAccountStatus());
		adto.setAccountType(accountrequest.getAccountType());
		adto.setAvailableBalance(accountrequest.getAvailableBalance());
		Users user = usermapper.convertToEntity(userservice.getUser(accountrequest.getUserId()));
		adto.setUser(user);
		return ResponseEntity.ok(accountservice.save(adto));
	}

}
