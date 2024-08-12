package com.exam.account.service.consumer;

import com.exam.account.controller.dto.AccountDto;
import com.exam.account.model.StatusAccount;
import com.exam.account.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsumerAccountService {

    private final AccountService accountService;

    private final ObjectMapper objectMapper;

    public ConsumerAccountService(AccountService accountService,
                                  ObjectMapper objectMapper) {
        this.accountService = accountService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "new-clients")
    public void listen(String account) throws JsonProcessingException {
        AccountDto accountDto = objectMapper.readValue(account, AccountDto.class);

        accountDto.setStatusAccount(StatusAccount.ACTIVE);
        accountDto.setBalance(new BigDecimal(0));
        accountService.createAccount(accountDto);
    }

}
