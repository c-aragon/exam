package com.exam.account.service;

import com.exam.account.controller.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

    TransactionDto createTransaction(TransactionDto clientDto);

    TransactionDto editTransaction(Long id, TransactionDto clientDto);

    void deleteTransaction(Long id);

    TransactionDto getTransaction(Long id);

    List<TransactionDto> getTransactions();

}
