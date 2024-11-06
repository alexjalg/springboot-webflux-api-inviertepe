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
@Document(collection ="credit_card")
public class CreditCard {

	@Id
	private String id;
	private String cardNumber;
	private String creditLimit;
	private double balance;
	private String status;
	private List<Transaction> transactions;

}
