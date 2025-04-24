package com.inviertepe.delegate.impl;

import java.net.URI;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import com.inviertepe.document.BankAccount;
import com.inviertepe.document.Customer;
import com.inviertepe.mapper.IBankAccountMapper;
import com.inviertepe.mapper.ICreditCardMapper;
import com.inviertepe.mapper.ICustomerMapper;
import com.inviertepe.mapper.ILoanMapper;
import com.inviertepe.server.api.ProductApiDelegate;
import com.inviertepe.server.dto.BalanceResponse;
import com.inviertepe.server.dto.BankAccountRequest;
import com.inviertepe.server.dto.BankAccountResponse;
import com.inviertepe.server.dto.BankAccountResponse.TypeBankAccountEnum;
import com.inviertepe.server.dto.CreditCardRequest;
import com.inviertepe.server.dto.CreditCardResponse;
import com.inviertepe.server.dto.LoanRequest;
import com.inviertepe.server.dto.LoanResponse;
import com.inviertepe.service.IBankAccountService;
import com.inviertepe.service.ICreditCardService;
import com.inviertepe.service.ICustomerService;
import com.inviertepe.service.ILoanService;

import reactor.core.publisher.Mono;

@Service
public class ProductApiDelegateImpl implements ProductApiDelegate {

	private static final Logger log = LoggerFactory.getLogger(ProductApiDelegateImpl.class);
	
	@Autowired
	private IBankAccountService bankAccountService;
	@Autowired
	private ICreditCardService creditCardService;
	@Autowired
	private ILoanService loanService;
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private ICreditCardMapper creditCardMapper;
	@Autowired
	private ILoanMapper loanMapper;
	@Autowired
	private ICustomerMapper customerMapper;
	@Autowired
	private IBankAccountMapper bankAccountMapper;

	@Override
	public Mono<ResponseEntity<BankAccountResponse>> addBankAccount(Mono<BankAccountRequest> bankAccountRequest,
			ServerWebExchange exchange) {
		return bankAccountRequest
				.flatMap(request -> {
					var bankAccount = bankAccountMapper.toBankAccount(request);
					return bankAccountService.save(bankAccount);
				})
				.map(saved -> {
					var response = bankAccountMapper.toBankAccountResponse(saved);
					return ResponseEntity
							.created(URI.create(exchange.getRequest().getURI().toString().concat("/").concat(response.getId())))
							.contentType(MediaType.APPLICATION_JSON)
							.body(response);
				});

	}
	
	@Override
	public Mono<ResponseEntity<CreditCardResponse>> addCreditCard(Mono<CreditCardRequest> creditCardRequest,
        ServerWebExchange exchange) {
		
		return creditCardRequest
				.flatMap(request -> {
					var creditCard = creditCardMapper.toCreditCard(request);
					var customerId = request.getCustomerId();
					return creditCardService.save( creditCard, customerId);
				})
				.map(savedCreditCard -> {
					var response = creditCardMapper.toCreditCardResponse(savedCreditCard);
					return ResponseEntity
							.created(URI.create(exchange.getRequest().getURI().toString().concat("/").concat(response.getId())))
							.contentType(MediaType.APPLICATION_JSON)
							.body(response);
				});

    }
	
	@Override
	public Mono<ResponseEntity<LoanResponse>> addLoan(Mono<LoanRequest> loanRequest,
        ServerWebExchange exchange) {
		return loanRequest
				.flatMap(request -> {
					var loan = loanMapper.toLoan(request);
					return loanService.save( loan);
				})
				.map(savedLoan -> {
					var response = loanMapper.toLoanResponse(savedLoan);
					return ResponseEntity
							.created(URI.create(exchange.getRequest().getURI().toString().concat("/").concat(response.getId())))
							.contentType(MediaType.APPLICATION_JSON)
							.body(response);
				});
    }

	@Override
    public Mono<ResponseEntity<BalanceResponse>> getProdutsById(String customerId,
        ServerWebExchange exchange) {
		
        return customerService.findById(customerId)
        		.map(data -> {
        			var response = customerMapper.toBalanceResponse(data);
        			return ResponseEntity
        					.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(response);
        		})
        		.defaultIfEmpty(ResponseEntity.notFound().build());

    }
}
