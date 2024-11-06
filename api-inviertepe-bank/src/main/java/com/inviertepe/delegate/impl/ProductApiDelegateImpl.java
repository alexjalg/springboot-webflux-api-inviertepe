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
import com.inviertepe.server.api.ProductApiDelegate;
import com.inviertepe.server.dto.BankAccountRequest;
import com.inviertepe.server.dto.BankAccountResponse;
import com.inviertepe.server.dto.BankAccountResponse.TypeBankAccountEnum;
import com.inviertepe.server.dto.CreditCardRequest;
import com.inviertepe.server.dto.CreditCardResponse;
import com.inviertepe.service.IBankAccountService;
import com.inviertepe.service.ICreditCardService;

import reactor.core.publisher.Mono;

@Service
public class ProductApiDelegateImpl implements ProductApiDelegate {

	private static final Logger log = LoggerFactory.getLogger(ProductApiDelegateImpl.class);
	
	@Autowired
	private IBankAccountService bankAccountService;
	
	@Autowired
	private ICreditCardService creditCardService;

	@Override
	public Mono<ResponseEntity<BankAccountResponse>> addBankAccount(Mono<BankAccountRequest> bankAccountRequest,
			ServerWebExchange exchange) {
		Mono<Customer> monoDocument = Mono.just(new Customer());
		return  monoDocument
				.zipWith(bankAccountRequest, (document, request) -> {
					document.setId(request.getCustomerId());
					BankAccount bankAccount = new BankAccount();
					bankAccount.setType(request.getTypeBankAccount().getValue());
					bankAccount.setBalance(request.getBalance());
					bankAccount.setSignatories(new ArrayList<>());
					bankAccount.getSignatories().addAll(request.getSignatories());
					bankAccount.setHolders(new ArrayList<>());
					bankAccount.getHolders().addAll(request.getHolders());
					document.setBankAccounts(new ArrayList<>());
					document.getBankAccounts().add(bankAccount);
					log.info(document.toString());
					return document;
					})
				.flatMap(bankAccountService::save)
				.map(c -> {
					BankAccountResponse response = new BankAccountResponse();
					response.setId(c.getId());
					response.setBankAccountNumber(c.getAccountNumber());
					c.getHolders().forEach(response::addHoldersItem);
					c.getSignatories().forEach(response::addSignatoriesItem);
					response.setTypeBankAccount(TypeBankAccountEnum.fromValue(c.getType()));
					response.setBalance(c.getBalance());
					return ResponseEntity
							.created(URI.create(exchange.getRequest().getURI().toString().concat("/").concat(response.getId())))
							.contentType(MediaType.APPLICATION_JSON)
							.body(response);
				});

	}
	
	@Override
	public Mono<ResponseEntity<CreditCardResponse>> addCreditCard(Mono<CreditCardRequest> creditCardRequest,
        ServerWebExchange exchange) {
		return null;
//        Mono<Void> result = Mono.empty();
//        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
//        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
//            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
//                String exampleString = "{ \"balance\" : 15000.0, \"creditCardNumber\" : \"1234123412341234\", \"creditLimit\" : 15000.0, \"id\" : 100000, \"type\" : \"personal\" }";
//                result = ApiUtil.getExampleResponse(exchange, MediaType.valueOf("application/json"), exampleString);
//                break;
//            }
//            if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
//                String exampleString = "<creditCard> <id>100000</id> <creditCardNumber>1234123412341234</creditCardNumber> <type>personal</type> <creditLimit>15000.0</creditLimit> <balance>15000.0</balance> </creditCard>";
//                result = ApiUtil.getExampleResponse(exchange, MediaType.valueOf("application/xml"), exampleString);
//                break;
//            }
//        }
//        return result.then(creditCardRequest).then(Mono.empty());

    }
}
