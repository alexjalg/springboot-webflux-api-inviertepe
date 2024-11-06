package com.inviertepe.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection ="transaction")
public class Transaction {

	@Id
	private String id;
	private String type;
	private double amount;

}
