package com.inviertepe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inviertepe.document.Customer;
import com.inviertepe.server.dto.CustomerRequest;
import com.inviertepe.server.dto.CustomerResponse;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
	
	@Mapping(target = "type", expression = "java(customerRequest.getType().getValue())")
	Customer toCustomer(CustomerRequest customerRequest);

	@Mapping(target = "type", expression = "java(com.inviertepe.server.dto.CustomerResponse.TypeEnum.fromValue(customer.getType()))")
	CustomerResponse toCustomerResponse(Customer customer);

}
