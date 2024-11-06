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
@Document(collection ="loan")
public class Loan {

	@Id
	private String id;
	private String accountNumber;
	private String type;
	private double amount;
	private double interestRate;
	private String status;
	private List<Transaction> transactions;

}
