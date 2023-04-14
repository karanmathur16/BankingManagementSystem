package com.in6225.spring.banking.corebankingsystem.service.impl.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.in6225.spring.banking.corebankingsystem.UserType;
import com.in6225.spring.banking.corebankingsystem.entities.Users;
import com.in6225.spring.banking.corebankingsystem.services.impl.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	ApplicationContext context;

	@Test
	void contextLoads() {
		UserServiceImpl userservice =context.getBean(UserServiceImpl.class);
		
		Users user1 = new Users();
		user1.setUsername("Karan Mathur2");
		user1.setEmail("karan@gmail.com");
		user1.setType(UserType.ADMIN);
		user1.setPassword("test1234");
		
		userservice.createUser(user1);
	}
}
