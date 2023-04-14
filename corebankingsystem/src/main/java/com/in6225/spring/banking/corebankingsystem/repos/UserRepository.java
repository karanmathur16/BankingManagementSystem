package com.in6225.spring.banking.corebankingsystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in6225.spring.banking.corebankingsystem.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
}
