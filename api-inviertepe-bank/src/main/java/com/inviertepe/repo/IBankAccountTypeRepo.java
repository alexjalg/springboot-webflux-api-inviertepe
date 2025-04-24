package com.inviertepe.repo;

import com.inviertepe.document.BankAccountType;

import reactor.core.publisher.Mono;

public interface IBankAccountTypeRepo extends IGenericRepo<BankAccountType, String> {

	 Mono<BankAccountType> findByName(String name);
	
}
