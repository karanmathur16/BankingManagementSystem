package com.in6225.spring.banking.corebankingsystem.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.in6225.spring.banking.corebankingsystem.TransactionType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transactions {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idtransactions;

	    private BigDecimal amount;

	    @Enumerated(EnumType.STRING)
	    private TransactionType transactionType;

	    private String transactionNumber;
	    
	    private LocalDateTime transactionDateTime;

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "account_id", referencedColumnName = "idaccounts")
	    private Accounts account;

		public Long getIdtransactions() {
			return idtransactions;
		}

		public void setIdtransactions(Long idtransactions) {
			this.idtransactions = idtransactions;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public TransactionType getTransactionType() {
			return transactionType;
		}

		public void setTransactionType(TransactionType transactionType) {
			this.transactionType = transactionType;
		}

		public String getTransactionNumber() {
			return transactionNumber;
		}

		public void setTransactionNumber(String transactionNumber) {
			this.transactionNumber = transactionNumber;
		}

		public LocalDateTime getTransactionDateTime() {
			return transactionDateTime;
		}

		public void setTransactionDateTime(LocalDateTime localDateTime) {
			this.transactionDateTime = localDateTime;
		}

		public Accounts getAccount() {
			return account;
		}

		public void setAccount(Accounts account) {
			this.account = account;
		}

		@Override
		public String toString() {
			return "Transactions [idtransactions=" + idtransactions + ", amount=" + amount + ", transactionType="
					+ transactionType + ", transactionNumber=" + transactionNumber + ", transactionDateTime="
					+ transactionDateTime + ", account=" + account + "]";
		}

			    

}
