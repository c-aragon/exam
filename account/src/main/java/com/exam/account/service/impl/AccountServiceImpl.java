package com.exam.account.service.impl;

import com.exam.account.controller.dto.AccountDto;
import com.exam.account.exception.EntityNotFoundException;
import com.exam.account.mapper.AccountMapper;
import com.exam.account.mapper.TransactionMapper;
import com.exam.account.model.Account;
import com.exam.account.repository.AccountRepository;
import com.exam.account.repository.TransactionRepository;
import com.exam.account.service.AccountService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository,
                              TransactionRepository transactionRepository,
                              TransactionMapper transactionMapper,
                              AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.accountMapper = accountMapper;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = accountMapper.accountDtoToAccount(accountDto);
        try {
            accountRepository.save(account);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(ex.getMessage());
        }
        return accountMapper.accountToAccountDto(account);
    }

    @Override
    public AccountDto editAccount(Long id, AccountDto accountDto) {
        Account account = getAccountEntity(id);
        account.setStatusAccount(accountDto.getStatusAccount());
        account.setBalance(accountDto.getBalance());
        account.setId(id);
        return accountMapper.accountToAccountDto(account);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = getAccountEntity(id);
        accountRepository.delete(account);
    }

    @Override
    public AccountDto getAccount(Long id) {
        Account account = getAccountEntity(id);
        return accountMapper.accountToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAccounts() {
        return accountRepository.findAll().stream().map(accountMapper::accountToAccountDto).toList();
    }

    public Account getAccountEntity(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return account.get();
        }
        throw new EntityNotFoundException(String.format("Account %d doesn't exists!", id));
    }
}
