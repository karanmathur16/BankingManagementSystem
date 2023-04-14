package com.in6225.spring.banking.corebankingsystem.entities;

import java.math.BigDecimal;

import com.in6225.spring.banking.corebankingsystem.AccountStatus;
import com.in6225.spring.banking.corebankingsystem.AccountType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Accounts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idaccounts;
	private String accountNumber;
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
	private BigDecimal availableBalance;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

	public long getIdaccounts() {
		return idaccounts;
	}

	public void setIdaccounts(long idaccounts) {
		this.idaccounts = idaccounts;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Accounts [idaccounts=" + idaccounts + ", accountNumber=" + accountNumber + ", accountType="
				+ accountType + ", accountStatus=" + accountStatus + ", availableBalance=" + availableBalance
				+ ", user=" + user + "]";
	}
	
	

}
