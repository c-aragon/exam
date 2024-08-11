package com.exam.account.mapper;

import com.exam.account.controller.dto.AccountDto;
import com.exam.account.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {

    TransactionMapper transactionMapper;

    public AccountMapperImpl(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Override
    public AccountDto accountToAccountDto(Account account) {
        if (account == null) {
            return null;
        }

        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setStatusAccount(account.getStatusAccount());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBalance(account.getBalance());
        accountDto.setIdClient(account.getIdClient());
        accountDto.setTransactions(account.getTransactions().stream().map(
                transaction -> transactionMapper.transactionToTransactionDto(transaction)).toList());
        return accountDto;
    }

    @Override
    public Account accountDtoToAccount(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        Account account = new Account();
        account.setStatusAccount(accountDto.getStatusAccount());
        account.setAccountType(accountDto.getAccountType());
        account.setBalance(accountDto.getAmount());
        account.setIdClient(accountDto.getIdClient());
        account.setTransactions(accountDto.getTransactions().stream().map(
                transaction -> transactionMapper.transactionDtoToTransaction(transaction)).toList());
        return account;
    }

}
