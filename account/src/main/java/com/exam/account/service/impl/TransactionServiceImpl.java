package com.exam.account.service.impl;

import com.exam.account.controller.dto.TransactionDto;
import com.exam.account.exception.EntityNotFoundException;
import com.exam.account.exception.InsufficientBalanceException;
import com.exam.account.exception.InvalidDataException;
import com.exam.account.exception.InvalidOperationException;
import com.exam.account.mapper.TransactionMapper;
import com.exam.account.model.Account;
import com.exam.account.model.StatusTransaction;
import com.exam.account.model.Transaction;
import com.exam.account.model.TransactionType;
import com.exam.account.repository.AccountRepository;
import com.exam.account.repository.TransactionRepository;
import com.exam.account.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(AccountRepository accountRepository,
                                  TransactionRepository transactionRepository,
                                  TransactionMapper transactionMapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    @Transactional
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Optional<Account> account = accountRepository.findById(transactionDto.getAccountId());
        if (account.isEmpty()) {
            throw new EntityNotFoundException(String.format("Transaction %d doesn't exists!", transactionDto.getAccountId()));
        }
        Transaction transaction = Transaction.builder()
                .transactionType(transactionDto.getTransactionType())
                .amount(transactionDto.getAmount())
                .build();

        if (transactionDto.getAmount().signum() < 0) {
            transaction.setTransactionType(TransactionType.RETIRO);
            if (transactionDto.getAmount().abs().compareTo(account.get().getBalance()) > 0) {
                throw new InsufficientBalanceException("The transaction cannot be applied: insufficient balance.");
            }
        } else if (transactionDto.getAmount().signum() == 0) {
            throw new InvalidOperationException("The transaction cannot be applied: " +
                    "You cannot make transactions with value 0");
        } else {
            transaction.setTransactionType(TransactionType.DEPOSITO);
        }

        transaction.setAccount(account.get());
        transaction.setDate(LocalDateTime.now());
        transaction.setOldBalance(account.get().getBalance());
        transaction.setBalance(account.get().getBalance().add(transactionDto.getAmount()));
        transaction.setStatusTransaction(StatusTransaction.APPLIED);
        try {
            transactionRepository.saveAndFlush(transaction);
            account.get().setBalance(transaction.getBalance());
            accountRepository.saveAndFlush(account.get());
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(ex.getMessage());
        }
        return transactionMapper.transactionToTransactionDto(transaction);
    }

    @Override
    public TransactionDto editTransaction(Long id, TransactionDto transactionDto) {
        Transaction transaction = getTransactionEntity(id);
        if (transactionDto.getAccountId().longValue() != transaction.getAccount().getId().longValue()) {
            throw new InvalidDataException("Account number doesn't match with transaction");
        }
        if (transactionDto.getAmount().compareTo(transaction.getAmount()) != 0) {
            throw new InvalidDataException("Amount doesn't match with transaction");
        }
        transaction.setStatusTransaction(transactionDto.getStatusTransaction());
        transactionRepository.save(transaction);
        return transactionMapper.transactionToTransactionDto(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        Transaction transaction = getTransactionEntity(id);
        transactionRepository.delete(transaction);
    }

    @Override
    public TransactionDto getTransaction(Long id) {
        Transaction transaction = getTransactionEntity(id);
        return transactionMapper.transactionToTransactionDto(transaction);
    }

    @Override
    public List<TransactionDto> getTransactions() {
        return transactionRepository.findAll().stream().map(transactionMapper::transactionToTransactionDto).toList();
    }

    public Transaction getTransactionEntity(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            return transaction.get();
        }
        throw new EntityNotFoundException(String.format("Transaction %d doesn't exists!", id));
    }


}
