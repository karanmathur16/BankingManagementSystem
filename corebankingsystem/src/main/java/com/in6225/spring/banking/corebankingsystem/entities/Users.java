package com.in6225.spring.banking.corebankingsystem.entities;

import com.in6225.spring.banking.corebankingsystem.UserType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idusers;
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserType type;
	private String email;
//	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Accounts> accounts;
	
	public long getIdusers() {
		return idusers;
	}
	public void setIdusers(long idusers) {
		this.idusers = idusers;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
//	public List<Accounts> getAccounts() {
//		return accounts;
//	}
//	public void setAccounts(List<Accounts> accounts) {
//		this.accounts = accounts;
//	}
	
	@Override
	public String toString() {
		return "Users [idusers=" + idusers + ", username=" + username + ", password=" + password + ", type=" + type
				+ ", email=" + email + "]";
	}
	
//	public Users(String username, String password, UserType type, String email) {
//		super();
//		this.username = username;
//		this.password = password;
//		this.type = type;
//		this.email = email;
//	}

}
