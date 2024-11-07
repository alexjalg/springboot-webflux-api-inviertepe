package com.inviertepe.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inviertepe.document.Loan;
import com.inviertepe.repo.ICustomerRepo;
import com.inviertepe.repo.IGenericRepo;
import com.inviertepe.repo.ILoanRepo;
import com.inviertepe.service.ILoanService;

import reactor.core.publisher.Mono;

@Service
public class LoanServiceImpl extends CRUDImpl<Loan, String> implements ILoanService {

	@Autowired
	private ILoanRepo repo;
	@Autowired
	private ICustomerRepo repoCustomer;

	private static final Logger log = LoggerFactory.getLogger(LoanServiceImpl.class);
	
	@Override
	protected IGenericRepo<Loan, String> getRepo() {
		return repo;
	}
	
	@Override
	public Mono<Loan> save(Loan loan){
		log.info(loan.toString());
		return repo.save(loan)
				.flatMap(savedLoan -> {
					return repoCustomer.findById(loan.getCustomerId())
							.flatMap(customer -> {
								if(customer.getLoans() == null) {
									customer.setLoans(new ArrayList<>());
								}
								customer.getLoans().add(savedLoan);
								return repoCustomer.save(customer);
							})
							.thenReturn(savedLoan);
				});
	}

}
