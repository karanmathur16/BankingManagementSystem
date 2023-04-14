package com.in6225.spring.banking.corebankingsystem.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in6225.spring.banking.corebankingsystem.entities.Accounts;
import com.in6225.spring.banking.corebankingsystem.entities.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
	Optional<List<Transactions>> findByAccount(Accounts account);
	Optional<List<Transactions>> findBytransactionNumber(String transactionNumber);
}
