package com.inviertepe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.inviertepe.document.CreditCard;
import com.inviertepe.server.dto.CreditCardRequest;
import com.inviertepe.server.dto.CreditCardResponse;

@Mapper(componentModel = "spring")
public interface ICreditCardMapper {
	
	
	@Mappings({
		@Mapping(target = "type", expression = "java(request.getType().getValue())"),
		@Mapping(target = "balance", ignore = true),
		@Mapping(target = "creditCardNumber", ignore = true),
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "status", ignore = true),
		@Mapping(target = "transactions", ignore = true)
	    })
	CreditCard toCreditCard(CreditCardRequest request);
	
	@Mapping(target = "type", expression = "java(com.inviertepe.server.dto.CreditCardResponse.TypeEnum.fromValue(creditCard.getType()))")
	CreditCardResponse toCreditCardResponse(CreditCard creditCard);

}
