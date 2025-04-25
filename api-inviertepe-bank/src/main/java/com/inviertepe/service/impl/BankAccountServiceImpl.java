package com.inviertepe.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inviertepe.document.BankAccount;
import com.inviertepe.mapper.IBankAccountMapper;
import com.inviertepe.repo.IBankAccountRepo;
import com.inviertepe.repo.IBankAccountTypeRepo;
import com.inviertepe.repo.ICustomerRepo;
import com.inviertepe.repo.IGenericRepo;
import com.inviertepe.server.dto.BankAccountRequest;
import com.inviertepe.service.IBankAccountService;
import com.inviertepe.util.CodeGenerator;

import reactor.core.publisher.Mono;

@Service
public class BankAccountServiceImpl extends CRUDImpl<BankAccount, String> implements IBankAccountService {

	@Autowired
	private IBankAccountRepo repo;
	@Autowired
	private ICustomerRepo repoCustomer;
	@Autowired
	private IBankAccountTypeRepo repoBankAccountType;
	
	@Autowired
	private IBankAccountMapper bankAccountMapper;
	
	@Override
	protected IGenericRepo<BankAccount, String> getRepo() {
		return repo;
	}
	
	@Override
	public Mono<BankAccount> save(BankAccount request){
		return CodeGenerator.generateBankAccountNumber()
				.flatMap(accountNumber -> {
					request.setBankAccountNumber(accountNumber);
					return repo.save(request);
				})
				.flatMap(savedAccount -> {
					return repoCustomer.findById(request.getCustomerId())
							.flatMap(customer -> {
								if(customer.getBankAccounts() == null) 
									customer.setBankAccounts(new ArrayList<>());
								customer.getBankAccounts().add(savedAccount);
								return repoCustomer.save(customer);
								})
							.thenReturn(savedAccount);
				});

	}

	@Override
	public Mono<BankAccount> toBankAccount(BankAccountRequest dto) {
		BankAccount obj = bankAccountMapper.toBankAccount(dto);
        return repoBankAccountType.findByName(dto.getTypeBankAccount().getValue())
            .map(product -> {
            	obj.setType(product);
                return obj;
            });
	}

}
