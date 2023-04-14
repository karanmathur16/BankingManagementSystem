package com.in6225.spring.banking.corebankingsystem.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in6225.spring.banking.corebankingsystem.entities.Accounts;
import com.in6225.spring.banking.corebankingsystem.entities.Users;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
	Optional<Accounts> findByAccountNumber(String accountNumber);
	Optional<List<Accounts>> findByUser(Users user);
}
