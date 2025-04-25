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
	public Mono<CreditCard> save(CreditCard request){
		return CodeGenerator.generateCreditCardNumber()
				.flatMap(creditCardNumber -> {
					request.setCreditCardNumber(creditCardNumber);
					request.setBalance(request.getCreditLimit());
					return repo.save(request);
				})
				.flatMap(savedCreditCard -> {
					log.info(savedCreditCard.toString());
					return repoCustomer.findById(request.getCustomerId())
							.flatMap(customer -> {
								if(customer.getCreditCards() == null) {
									customer.setCreditCards(new ArrayList<>());
								}
								customer.getCreditCards().add(savedCreditCard);
								return repoCustomer.save(customer);
							})
							.thenReturn(savedCreditCard);
				});
	}

}
