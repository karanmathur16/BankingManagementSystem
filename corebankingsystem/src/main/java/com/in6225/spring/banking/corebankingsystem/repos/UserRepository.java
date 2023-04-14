package com.in6225.spring.banking.corebankingsystem.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in6225.spring.banking.corebankingsystem.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByusername(String username);
}
