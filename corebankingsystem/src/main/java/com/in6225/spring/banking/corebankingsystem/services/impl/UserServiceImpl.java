package com.in6225.spring.banking.corebankingsystem.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in6225.spring.banking.corebankingsystem.dto.UsersDTO;
import com.in6225.spring.banking.corebankingsystem.entities.Users;
import com.in6225.spring.banking.corebankingsystem.mapper.UserMapping;
import com.in6225.spring.banking.corebankingsystem.repos.UserRepository;
import com.in6225.spring.banking.corebankingsystem.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserMapping usermapper = new UserMapping();
	
	@Autowired
	private UserRepository userrepo;

	@Override
	public List<UsersDTO> findall() {
		return(usermapper.convertToDtoList(userrepo.findAll()));
	}

	@Override
	public UsersDTO getUser(Long id) {
		Users user = new Users();
		user = userrepo.findById(id).get();
		return (usermapper.convertToDto(user));
	}

	@Override
	public UsersDTO updateUser(Users user) {
		userrepo.save(user);
		return (usermapper.convertToDto(user));
	}

	@Override
	public UsersDTO createUser(Users user) {
		userrepo.save(user);
		return (usermapper.convertToDto(user));
	}

}
