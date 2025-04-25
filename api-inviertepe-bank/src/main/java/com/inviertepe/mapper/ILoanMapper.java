package com.inviertepe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.inviertepe.document.Loan;
import com.inviertepe.server.dto.LoanRequest;
import com.inviertepe.server.dto.LoanResponse;

@Mapper(componentModel = "spring")
public interface ILoanMapper {
	
	@Mappings({
		@Mapping(target = "type", expression = "java(request.getType().getValue())"),
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "status", ignore = true),
		@Mapping(target = "transactions", ignore = true)
	    })
	Loan toLoan(LoanRequest request);

	@Mapping(target = "type", expression = "java(com.inviertepe.server.dto.LoanResponse.TypeEnum.fromValue(loan.getType()))")
	LoanResponse toLoanResponse(Loan loan);

}
