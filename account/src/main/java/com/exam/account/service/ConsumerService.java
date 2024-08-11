package com.exam.account.service;

import com.exam.account.controller.dto.AccountDto;
import com.exam.account.controller.dto.TransactionDto;
import com.exam.account.model.StatusAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsumerService {

    private final AccountService accountService;

    private final TransactionService transactionService;

    private final ObjectMapper objectMapper;

    public ConsumerService(AccountService accountService,
                           TransactionService transactionService,
                           ObjectMapper objectMapper) {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "new-clients")
    public void listen(String account) throws JsonProcessingException {
        System.out.println(account);

        AccountDto accountDto = objectMapper.readValue(account, AccountDto.class);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(accountDto.getAmount());

        accountDto.setStatusAccount(StatusAccount.ACTIVE);
        accountDto.setAmount(new BigDecimal(0));
        accountDto = accountService.createAccount(accountDto);

        transactionDto.setAccountId(accountDto.getId());

        transactionService.createTransaction(transactionDto);
    }

}
