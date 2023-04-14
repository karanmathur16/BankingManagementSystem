package com.in6225.spring.banking.corebankingsystem.service.impl.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.in6225.spring.banking.corebankingsystem.controller.request.FundsTransferRequest;
import com.in6225.spring.banking.corebankingsystem.controller.response.FundTransferResponse;
import com.in6225.spring.banking.corebankingsystem.services.impl.TransactionServiceImpl;

@SpringBootTest
public class TransactionServiceTest {
	
	@Autowired
	ApplicationContext context;
	
	
	@Test
	void contextLoads() {
	TransactionServiceImpl transervice =context.getBean(TransactionServiceImpl.class);
	FundsTransferRequest ftrequest = new FundsTransferRequest();
	ftrequest.setAmount(BigDecimal.valueOf(50));
	ftrequest.setFromAccount("123454132");
	ftrequest.setToAccount("0000916151");
	
	FundTransferResponse ftresponse = new FundTransferResponse(null, null);
	
	ftresponse = transervice.fundsTransfer(ftrequest);
	
	System.out.println(ftresponse);
	
	}
	

}
