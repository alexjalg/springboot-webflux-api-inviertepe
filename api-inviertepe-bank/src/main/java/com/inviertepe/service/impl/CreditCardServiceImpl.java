package com.inviertepe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inviertepe.document.CreditCard;
import com.inviertepe.repo.ICreditCardRepo;
import com.inviertepe.repo.ICustomerRepo;
import com.inviertepe.repo.IGenericRepo;
import com.inviertepe.service.ICreditCardService;

import reactor.core.publisher.Mono;

@Service
public class CreditCardServiceImpl extends CRUDImpl<CreditCard, String> implements ICreditCardService {

	@Autowired
	private ICreditCardRepo repo;
	@Autowired
	private ICustomerRepo repoCustomer;

	@Override
	protected IGenericRepo<CreditCard, String> getRepo() {
		return repo;
	}
	
	@Override
	public Mono<CreditCard> save(CreditCard creditCard, String customerId){
		return null;
//		return CodeGenerator.generateCreditCardNumber()
//				.flatMap(accountNumber -> {
//					request.getCreditCards().get(0).setAccountNumber(accountNumber);
//					return repo.save(request.getCreditCards().get(0));
//				})
//				.flatMap(savedAccount -> {
//					return repoCustomer.findById(request.getId())
//							.flatMap(customer -> {
//								if(customer.getCreditCards() == null) 
//									customer.setCreditCards(new ArrayList<>());
//								customer.getCreditCards().add(savedAccount);
//								return repoCustomer.save(customer);
//								})
//							.thenReturn(savedAccount);
//				});
	}

}
