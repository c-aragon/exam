package com.exam.account.controller;

import com.exam.account.controller.dto.AccountDto;

import java.util.List;

public interface AccountController {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto editAccount(Long id, AccountDto accountDto);

    void deleteAccount(Long id);

    AccountDto getAccount(Long id);

    List<AccountDto> getAccounts();

}
