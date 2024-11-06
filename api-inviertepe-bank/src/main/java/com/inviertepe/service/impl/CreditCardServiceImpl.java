package com.inviertepe.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inviertepe.document.CreditCard;
import com.inviertepe.repo.ICreditCardRepo;
import com.inviertepe.repo.ICustomerRepo;
import com.inviertepe.repo.IGenericRepo;
import com.inviertepe.service.ICreditCardService;
import com.inviertepe.util.CodeGenerator;

import reactor.core.publisher.Mono;

@Service
public class CreditCardServiceImpl extends CRUDImpl<CreditCard, String> implements ICreditCardService {

	@Autowired
	private ICreditCardRepo repo;
	@Autowired
	private ICustomerRepo repoCustomer;

	private static final Logger log = LoggerFactory.getLogger(CreditCardServiceImpl.class);
	
	@Override
	protected IGenericRepo<CreditCard, String> getRepo() {
		return repo;
	}
	
	@Override
	public Mono<CreditCard> save(CreditCard creditCard, String customerId){
		return CodeGenerator.generateCreditCardNumber()
				.flatMap(creditCardNumber -> {
					creditCard.setCreditCardNumber(creditCardNumber);
					creditCard.setBalance(creditCard.getCreditLimit());
					log.info(creditCardNumber);
					return repo.save(creditCard);
				})
				.flatMap(savedCreditCard -> {
					return repoCustomer.findById(customerId)
							.flatMap(customer -> {
								if(customer.getCreditCards() == null) {
									customer.setCreditCards(new ArrayList<>());
								}
								customer.getCreditCards().add(creditCard);
								return repoCustomer.save(customer);
							})
							.thenReturn(savedCreditCard);
				});
	}

}
