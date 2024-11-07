package com.inviertepe.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection ="loan")
public class Loan {

	@Id
	private String id;
	private String type;
	private double amount;
	private double interestRate;
	private String status;
	private int termMonths;
	private List<Transaction> transactions;
	@Transient
	private String customerId;
	
}
