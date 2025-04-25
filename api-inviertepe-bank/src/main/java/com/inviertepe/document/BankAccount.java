package com.inviertepe.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	private String bankAccountNumber;
	private List<String> holders;
	private List<String> signatories;
	private BankAccountType type;
	private double balance;
	private String status;
	private List<Transaction> transactions;
	
	@Transient
	@NotNull
	@NotBlank(message = "El codigo de cliente es obligatorio")
	private String customerId;

}
