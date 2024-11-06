package com.inviertepe.util;

import java.util.Random;

import reactor.core.publisher.Mono;

public class CodeGenerator {

	static String BANK_CODE = "123";
	static String PLACE_CODE = "12";

	public static Mono<String> generateBankAccountNumber() {
		// Genera los últimos 14 dígitos aleatorios
		Random random = new Random();
		StringBuilder accountNumber = new StringBuilder();
		for (int i = 0; i < 14; i++) {
			accountNumber.append(random.nextInt(10)); // Genera un dígito aleatorio (0-9)
		}
		// Combina las partes para formar el número de cuenta completa
		return Mono.just(BANK_CODE + PLACE_CODE + accountNumber.toString());
	}

}
