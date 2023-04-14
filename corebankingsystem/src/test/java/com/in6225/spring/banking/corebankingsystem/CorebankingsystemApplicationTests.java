package com.in6225.spring.banking.corebankingsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.in6225.spring.banking.corebankingsystem.entities.Users;
import com.in6225.spring.banking.corebankingsystem.repos.UserRepository;

@SpringBootTest
class CorebankingsystemApplicationTests {
	
	@Autowired
	ApplicationContext context;

	@Test
	void contextLoads() {
		UserRepository userrepo = context.getBean(UserRepository.class);
		Users user = new Users();
		
		user.setUsername("Test User");
		user.setEmail("testemail@gmail.com");
		user.setPassword("testpassword123");
		user.setType(UserType.CUSTOMER);
		
		userrepo.save(user);
	}

}
