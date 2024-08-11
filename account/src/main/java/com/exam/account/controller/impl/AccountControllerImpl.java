package com.exam.account.controller.impl;

import com.exam.account.controller.AccountController;
import com.exam.account.controller.dto.AccountDto;
import com.exam.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/cuentas")
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;

    public AccountControllerImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto createAccount(@Valid @RequestBody AccountDto accountDto) {
        return accountService.createAccount(accountDto);
    }

    @PatchMapping("/{id}")
    @Override
    public AccountDto editAccount(@PathVariable Long id, @Valid @RequestBody AccountDto accountDto) {
        return accountService.editAccount(id, accountDto);
    }

    @DeleteMapping({"/{id}"})
    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @GetMapping({"/{id}"})
    @Override
    public AccountDto getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @GetMapping
    @Override
    public List<AccountDto> getAccounts() {
        return accountService.getAccounts();
    }
}
