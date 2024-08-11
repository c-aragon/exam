package com.exam.account.controller;

import com.exam.account.controller.dto.TransactionDto;

import java.util.List;

public interface TransactionController {

    TransactionDto createTransaction(TransactionDto transactionDto);

    TransactionDto editTransaction(Long id, TransactionDto transactionDto);

    void deleteTransaction(Long id);

    TransactionDto getTransaction(Long id);

    List<TransactionDto> getTransactions();

}
