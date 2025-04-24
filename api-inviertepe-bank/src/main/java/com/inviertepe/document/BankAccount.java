package com.inviertepe.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection ="bank_account")
public class BankAccount {
	
	@Id
	private String id;
	private String accountNumber;
	private List<String> holders;
	private List<String> signatories;
	private BankAccountType type;
	private double balance;
	private String status;
	private List<Transaction> transactions;

}
