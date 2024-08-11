package com.exam.account.mapper;

import com.exam.account.controller.dto.AccountDto;
import com.exam.account.model.Account;

public interface AccountMapper {

    AccountDto accountToAccountDto(Account account);

    Account accountDtoToAccount(AccountDto accountDto);
}
