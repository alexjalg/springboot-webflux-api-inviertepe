package com.inviertepe.delegate.impl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import com.inviertepe.mapper.ICustomerMapper;
import com.inviertepe.server.api.CustomerApiDelegate;
import com.inviertepe.server.dto.CustomerRequest;
import com.inviertepe.server.dto.CustomerResponse;
import com.inviertepe.service.ICustomerService;

import reactor.core.publisher.Mono;

@Service
public class CustomerApiDelegateImpl implements CustomerApiDelegate {

	@Autowired
	private ICustomerService service;
	
	@Autowired
	private ICustomerMapper customerMapper; 
	
	@Override
	public Mono<ResponseEntity<CustomerResponse>> addCustomer(Mono<CustomerRequest> customerRequest,
			ServerWebExchange exchange) {

		return  customerRequest
				.flatMap(request -> service.save(customerMapper.toCustomer(request)))
				.map(savedCustomer -> customerMapper.toCustomerResponse(savedCustomer))
				.map(response -> ResponseEntity
						.created(URI.create(exchange.getRequest().getURI().toString().concat("/").concat(response.getId())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(response));
	}
	
	

}
