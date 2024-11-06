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
@Document(collection ="customer")
public class Customer {
	
	@Id
	private String id;
	private String name;
	private String type;
	private List<BankAccount> bankAccounts;
	private List<Loan> loans;
	private List<CreditCard> creditCards;

}
