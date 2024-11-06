package com.inviertepe.service;

import com.inviertepe.document.BankAccount;
import com.inviertepe.document.Customer;

import reactor.core.publisher.Mono;

public interface IBankAccountService extends ICRUD<BankAccount, String> {

	public Mono<BankAccount> save(Customer customer);
}
