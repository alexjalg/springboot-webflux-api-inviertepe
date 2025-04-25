package com.inviertepe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.inviertepe.document.Customer;
import com.inviertepe.server.dto.BalanceResponse;
import com.inviertepe.server.dto.CustomerRequest;
import com.inviertepe.server.dto.CustomerResponse;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
	
	@Mappings({
		@Mapping(target = "type", expression = "java(customerRequest.getType().getValue())"),
		@Mapping(target = "bankAccounts", ignore = true),
		@Mapping(target = "creditCards", ignore = true),
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "loans", ignore = true)
		})
	Customer toCustomer(CustomerRequest customerRequest);

	@Mapping(target = "type", expression = "java(com.inviertepe.server.dto.CustomerResponse.TypeEnum.fromValue(customer.getType()))")
	CustomerResponse toCustomerResponse(Customer customer);
	
	@Mapping(target = "customerId", expression = "java(customer.getId())")
	BalanceResponse toBalanceResponse(Customer customer);

}
