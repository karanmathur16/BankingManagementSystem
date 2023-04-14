package com.in6225.spring.banking.corebankingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in6225.spring.banking.corebankingsystem.controller.request.LoginRequest;
import com.in6225.spring.banking.corebankingsystem.entities.Users;
import com.in6225.spring.banking.corebankingsystem.services.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userservice;
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/{id}")
    public ResponseEntity getUser(@PathVariable("id") String idstr) {
		Long id = Long.parseLong(idstr);
        return ResponseEntity.ok(userservice.getUser(id));
    }
	
	@SuppressWarnings("rawtypes")
	@GetMapping
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userservice.findall());
    }
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/create")
	public ResponseEntity createUser(@RequestBody Users user){
		return ResponseEntity.ok(userservice.createUser(user));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/update")
	public ResponseEntity updateUser(@RequestBody Users user){
		return ResponseEntity.ok(userservice.updateUser(user));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public ResponseEntity Login(@RequestBody LoginRequest request){
		String username = request.getUsername();
		String password = request.getPassword();
		String response = userservice.Login(username,password);
		if(response.equals("Success")) {
			return ResponseEntity.ok("Success");
		}
		else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    	
	}


}
