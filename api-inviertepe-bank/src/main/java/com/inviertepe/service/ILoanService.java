package com.inviertepe.service;

import com.inviertepe.document.Loan;

import reactor.core.publisher.Mono;

public interface ILoanService extends ICRUD<Loan, String> {

	public Mono<Loan> save(Loan loan);
}
