package com.exam.account.service.impl;

import com.exam.account.controller.dto.TransactionDto;
import com.exam.account.exception.EntityNotFoundException;
import com.exam.account.exception.InsufficientBalanceException;
import com.exam.account.exception.InvalidOperationException;
import com.exam.account.mapper.TransactionMapper;
import com.exam.account.model.Account;
import com.exam.account.model.StatusAccount;
import com.exam.account.model.StatusTransaction;
import com.exam.account.model.Transaction;
import com.exam.account.model.TransactionType;
import com.exam.account.repository.AccountRepository;
import com.exam.account.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class TransactionServiceUnitTest {

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionServiceImpl transactionService;

    @Test
    public void decreaseBalanceWithErrorTest() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAccountId(1L);
        transactionDto.setAmount(new BigDecimal(-5000));

        Mockito.when(accountRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(getAccount());
        Mockito.when(transactionMapper.transactionDtoToTransaction(ArgumentMatchers.any(TransactionDto.class)))
                .thenReturn(getTransactionDecreaseWithError());


        InsufficientBalanceException thrown = Assertions.assertThrows(
                InsufficientBalanceException.class,
                () -> transactionService.createTransaction(transactionDto),
                ""
        );

        Assertions.assertEquals("The transaction cannot be applied: insufficient balance.", thrown.getMessage());
    }

    @Test
    public void increaseWithInvalidAccount() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAccountId(1L);
        transactionDto.setAmount(new BigDecimal(-5000));

        Mockito.when(accountRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        EntityNotFoundException thrown = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> transactionService.createTransaction(transactionDto),
                ""
        );

        Assertions.assertEquals("Transaction 1 doesn't exists!", thrown.getMessage());
    }

    @Test
    public void increaseWithZeroAmount() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAccountId(1L);
        transactionDto.setAmount(new BigDecimal(0));

        Mockito.when(accountRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(getAccount());

        InvalidOperationException thrown = Assertions.assertThrows(
                InvalidOperationException.class,
                () -> transactionService.createTransaction(transactionDto),
                ""
        );

        Assertions.assertEquals("The transaction cannot be applied: " +
                "You cannot make transactions with value 0", thrown.getMessage());
    }

    private Optional<Account> getAccount() {
        Account account = new Account();
        account.setId(1L);
        account.setTransactions(new ArrayList<>());
        account.setBalance(new BigDecimal(100));
        account.setStatusAccount(StatusAccount.ACTIVE);

        return Optional.of(account);
    }

    private Transaction getTransactionDecreaseWithError() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAccount(getAccount().get());
        transaction.setAmount(new BigDecimal(-500));
        return transaction;
    }
}
