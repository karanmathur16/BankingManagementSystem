package com.in6225.spring.banking.corebankingsystem.services;

import java.util.List;

import com.in6225.spring.banking.corebankingsystem.dto.UsersDTO;
import com.in6225.spring.banking.corebankingsystem.entities.Users;

public interface UserService {
	List<UsersDTO> findall();
	UsersDTO getUser(Long id);
	UsersDTO updateUser(Users user);
	UsersDTO createUser(Users user);
}
