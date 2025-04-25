package com.inviertepe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.inviertepe.document.BankAccount;
import com.inviertepe.server.dto.BankAccountRequest;
import com.inviertepe.server.dto.BankAccountResponse;

@Mapper(componentModel = "spring")
public interface IBankAccountMapper {

	
	@Mappings({
		@Mapping(target = "type", ignore = true),
		@Mapping(target = "bankAccountNumber", ignore = true),
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "status", ignore = true),
		@Mapping(target = "transactions", ignore = true)
	    })
	BankAccount toBankAccount(BankAccountRequest request);

	@Mappings({
		 @Mapping(target = "typeBankAccount", expression = "java(com.inviertepe.server.dto.BankAccountResponse.TypeBankAccountEnum.fromValue(bankAccount.getType().getName()))")
	    })
	BankAccountResponse toBankAccountResponse(BankAccount bankAccount);
	
}
