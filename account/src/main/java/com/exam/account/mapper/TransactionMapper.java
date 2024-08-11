package com.exam.account.mapper;

import com.exam.account.controller.dto.TransactionDto;
import com.exam.account.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "accountId", source = "transaction.account.id")
    TransactionDto transactionToTransactionDto(Transaction transaction);

    Transaction transactionDtoToTransaction(TransactionDto transactionDto);
}
