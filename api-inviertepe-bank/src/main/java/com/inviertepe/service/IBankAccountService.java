package com.inviertepe.service;

import com.inviertepe.document.BankAccount;
import com.inviertepe.server.dto.BankAccountRequest;

import reactor.core.publisher.Mono;

public interface IBankAccountService extends ICRUD<BankAccount, String> {

	public Mono<BankAccount> save(BankAccount request);
	
	public Mono<BankAccount> toBankAccount(BankAccountRequest dto);

}
