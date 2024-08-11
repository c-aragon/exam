package com.exam.client.mapper.impl;

import com.exam.client.controller.dto.AccountDto;
import com.exam.client.controller.dto.AccountType;
import com.exam.client.mapper.AccountMapper;
import com.exam.client.service.message.AccountMessage;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {


    @Override
    public AccountDto accountMessage2AccountDto(AccountMessage accountMessage) {
        AccountDto accountDto = new AccountDto();
        accountDto.setIdClient(accountMessage.getIdClient());
        accountDto.setAccountType(AccountType.getAccountType(accountMessage.getAccountType()));
        accountDto.setAmount(accountMessage.getBalance());
        return accountDto;
    }

    @Override
    public AccountMessage accountDto2AccountMessage(AccountDto accountDto) {
        AccountMessage accountMessage = new AccountMessage();
        accountMessage.setAccountType(accountDto.getAccountType().ordinal());
        accountMessage.setBalance(accountDto.getAmount());
        accountMessage.setIdClient(accountDto.getIdClient());
        return accountMessage;
    }
}
