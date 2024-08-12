package com.exam.account.service.dto;

import com.exam.account.model.AccountType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@Setter
public class AccountReportDto {

    private Long accountId;

    private AccountType accountType;

    private BigDecimal balance;

    private List<TransactionDto> transactions;
}
