package com.inviertepe.service;

import com.inviertepe.document.Loan;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

public interface ILoanService extends ICRUD<Loan, String> {

	public Mono<Loan> save(@Valid Loan loan);
}
