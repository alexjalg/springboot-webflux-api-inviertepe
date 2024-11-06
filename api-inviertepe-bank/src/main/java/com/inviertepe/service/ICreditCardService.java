package com.inviertepe.service;

import com.inviertepe.document.CreditCard;

import reactor.core.publisher.Mono;

public interface ICreditCardService extends ICRUD<CreditCard, String> {

	public Mono<CreditCard> save(CreditCard creditCard, String customerId);
}
