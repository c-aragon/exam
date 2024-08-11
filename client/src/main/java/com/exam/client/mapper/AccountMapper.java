package com.exam.client.mapper;

import com.exam.client.controller.dto.AccountDto;
import com.exam.client.service.message.AccountMessage;

public interface AccountMapper {

    AccountDto accountMessage2AccountDto(AccountMessage accountMessage);

    AccountMessage accountDto2AccountMessage(AccountDto accountDto);

}
