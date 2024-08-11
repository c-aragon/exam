package com.exam.account.service;

import com.exam.account.controller.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto clientDto);

    AccountDto editAccount(Long id, AccountDto clientDto);

    void deleteAccount(Long id);

    AccountDto getAccount(Long id);

    List<AccountDto> getAccounts();

}
