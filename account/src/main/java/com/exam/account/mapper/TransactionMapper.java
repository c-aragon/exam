package com.exam.account.mapper;

import com.exam.account.controller.dto.TransactionDto;
import com.exam.account.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionDto transactionToTransactionDto(Transaction transaction) {
        return TransactionDto.builder()
                .statusTransaction(transaction.getStatusTransaction())
                .date(transaction.getDate())
                .transactionType(transaction.getTransactionType())
                .accountId(transaction.getAccount().getId())
                .amount(transaction.getAmount())
                .balance(transaction.getBalance())
                .id(transaction.getId())
                .oldBalance(transaction.getOldBalance())
                .build();
    }

    public Transaction transactionDtoToTransaction(TransactionDto transactionDto) {
        return Transaction.builder()
                .statusTransaction(transactionDto.getStatusTransaction())
                .date(transactionDto.getDate())
                .transactionType(transactionDto.getTransactionType())
                .account(null)
                .amount(transactionDto.getAmount())
                .balance(transactionDto.getBalance())
                .id(transactionDto.getId())
                .oldBalance(transactionDto.getOldBalance())
                .build();
    }

}
