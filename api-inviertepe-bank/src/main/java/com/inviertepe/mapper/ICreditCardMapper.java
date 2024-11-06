package com.inviertepe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inviertepe.document.CreditCard;
import com.inviertepe.server.dto.CreditCardRequest;
import com.inviertepe.server.dto.CreditCardResponse;

@Mapper(componentModel = "spring")
public interface ICreditCardMapper {
	
	@Mapping(target = "type", expression = "java(request.getType().getValue())")
	CreditCard toCreditCard(CreditCardRequest request);
	
	@Mapping(target = "type", expression = "java(com.inviertepe.server.dto.CreditCardResponse.TypeEnum.fromValue(creditCard.getType()))")
	CreditCardResponse toCreditCardResponse(CreditCard creditCard);

}
