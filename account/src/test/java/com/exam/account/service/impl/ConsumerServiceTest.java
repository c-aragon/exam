package com.exam.account.service.impl;

import com.exam.account.controller.dto.AccountDto;
import com.exam.account.controller.dto.TransactionDto;
import com.exam.account.model.Account;
import com.exam.account.model.AccountType;
import com.exam.account.model.StatusAccount;
import com.exam.account.model.Transaction;
import com.exam.account.repository.AccountRepository;
import com.exam.account.repository.TransactionRepository;
import com.exam.account.service.AccountService;
import com.exam.account.service.ConsumerService;
import com.exam.account.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@Transactional
@SpringBootTest
public class ConsumerServiceTest {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void receiveMessageForCreateAccount() throws JsonProcessingException {
        consumerService.listen("{\"accountType\":\"AHORROS\",\"amount\":500.0,\"idClient\":4}");

        Account account = accountRepository.findById(5L).get();

        Transaction transaction = transactionRepository.findById(1L).get();

        Assertions.assertEquals(4L, account.getIdClient());
        Assertions.assertEquals(AccountType.AHORROS, account.getAccountType());
        Assertions.assertEquals(StatusAccount.ACTIVE, account.getStatusAccount());

        Assertions.assertEquals(new BigDecimal("500.0"), transaction.getBalance());
        Assertions.assertEquals(new BigDecimal(0), transaction.getOldBalance());
        Assertions.assertEquals(5L, transaction.getAccount().getId());

    }

}
