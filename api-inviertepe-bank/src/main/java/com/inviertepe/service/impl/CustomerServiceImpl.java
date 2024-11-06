package com.inviertepe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inviertepe.document.Customer;
import com.inviertepe.repo.ICustomerRepo;
import com.inviertepe.repo.IGenericRepo;
import com.inviertepe.service.ICustomerService;

@Service
public class CustomerServiceImpl extends CRUDImpl<Customer, String> implements ICustomerService {

	@Autowired
	private ICustomerRepo repo;

	@Override
	protected IGenericRepo<Customer, String> getRepo() {
		return repo;
	}

}
