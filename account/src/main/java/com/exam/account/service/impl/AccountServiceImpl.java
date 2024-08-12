package com.exam.account.service.impl;

import com.exam.account.controller.dto.AccountDto;
import com.exam.account.controller.dto.TransactionDto;
import com.exam.account.exception.EntityNotFoundException;
import com.exam.account.exception.ServiceErrorException;
import com.exam.account.external.ClientClient;
import com.exam.account.mapper.AccountMapper;
import com.exam.account.model.Account;
import com.exam.account.model.StatusAccount;
import com.exam.account.repository.AccountRepository;
import com.exam.account.service.AccountService;
import com.exam.account.service.TransactionService;
import feign.FeignException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final TransactionService transactionService;

    private final AccountMapper accountMapper;

    private final ClientClient client;

    public AccountServiceImpl(AccountRepository accountRepository,
                              TransactionService transactionService,
                              AccountMapper accountMapper,
                              ClientClient client) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
        this.accountMapper = accountMapper;
        this.client = client;
    }

    @Transactional
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        try {
            client.getClient(accountDto.getIdClient());
        } catch (FeignException ex) {
            if (ex.status() == 404) {
                throw new EntityNotFoundException(String.format("Client %d doesn't exists!", accountDto.getIdClient()));
            } else {
                throw new ServiceErrorException("Error on get data from client service");
            }
        }

        Account account = accountMapper.accountDtoToAccount(accountDto);
        AccountDto response;
        try {
            accountRepository.save(account);
            response = accountMapper.accountToAccountDto(account);

            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setAmount(accountDto.getAmount());
            accountDto.setStatusAccount(StatusAccount.ACTIVE);
            accountDto.setBalance(new BigDecimal(0));
            transactionDto.setAccountId(response.getId());
            transactionService.createTransaction(transactionDto);

            account = accountRepository.findById(account.getId()).get();
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
