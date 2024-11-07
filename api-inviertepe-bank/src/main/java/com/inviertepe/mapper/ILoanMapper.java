package com.inviertepe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inviertepe.document.Loan;
import com.inviertepe.server.dto.LoanRequest;
import com.inviertepe.server.dto.LoanResponse;

@Mapper(componentModel = "spring")
public interface ILoanMapper {
	
	@Mapping(target = "type", expression = "java(request.getType().getValue())")
	Loan toLoan(LoanRequest request);

	@Mapping(target = "type", expression = "java(com.inviertepe.server.dto.LoanResponse.TypeEnum.fromValue(loan.getType()))")
	LoanResponse toLoanResponse(Loan loan);

}
