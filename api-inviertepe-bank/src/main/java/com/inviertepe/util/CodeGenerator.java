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
	
	public static Mono<String> generateCreditCardNumber() {
        return Mono.fromSupplier(() -> {
            // Primeros seis dígitos (por ejemplo, "459462" representa un BIN para Perú)
            String bin = "459462";
            StringBuilder cardNumber = new StringBuilder(bin);

            // Generar los siguientes 9 dígitos al azar (hasta el dígito 15)
            Random random = new Random();
            for (int i = 0; i < 9; i++) {
                cardNumber.append(random.nextInt(10));
            }

            // Calcular el último dígito (dígito de verificación) usando el algoritmo de Luhn
            int checkDigit = calculateLuhnCheckDigit(cardNumber.toString());
            cardNumber.append(checkDigit);

            return cardNumber.toString();
        });
    }

    // Algoritmo de Luhn para calcular el dígito de verificación
    private static int calculateLuhnCheckDigit(String cardNumberWithoutCheckDigit) {
        int sum = 0;
        boolean alternate = true;
        
        // Recorrer el número en orden inverso
        for (int i = cardNumberWithoutCheckDigit.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(String.valueOf(cardNumberWithoutCheckDigit.charAt(i)));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        return (10 - (sum % 10)) % 10;
    }

}
