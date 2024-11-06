package com.inviertepe.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inviertepe.document.BankAccount;
import com.inviertepe.document.Customer;
import com.inviertepe.repo.IBankAccountRepo;
import com.inviertepe.repo.ICustomerRepo;
import com.inviertepe.repo.IGenericRepo;
import com.inviertepe.service.IBankAccountService;
import com.inviertepe.util.CodeGenerator;

import reactor.core.publisher.Mono;

@Service
public class BankAccountServiceImpl extends CRUDImpl<BankAccount, String> implements IBankAccountService {

	@Autowired
	private IBankAccountRepo repo;
	@Autowired
	private ICustomerRepo repoCustomer;

	@Override
	protected IGenericRepo<BankAccount, String> getRepo() {
		return repo;
	}
	
	@Override
	public Mono<BankAccount> save(Customer request){
		return CodeGenerator.generateBankAccountNumber()
				.flatMap(accountNumber -> {
					request.getBankAccounts().get(0).setAccountNumber(accountNumber);
					return repo.save(request.getBankAccounts().get(0));
				})
				.flatMap(savedAccount -> {
					return repoCustomer.findById(request.getId())
							.flatMap(customer -> {
								if(customer.getBankAccounts() == null) 
									customer.setBankAccounts(new ArrayList<>());
								customer.getBankAccounts().add(savedAccount);
								return repoCustomer.save(customer);
								})
							.thenReturn(savedAccount);
				});

	}

}
