package com.inviertepe.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "bank_account_type")
public class BankAccountType {

	@Id
	private String id;
	private String name;
	private double maintenanceFee;// Comisión de mantenimiento.
	private int movementLimit;// Límite de movimientos mensuales

}
