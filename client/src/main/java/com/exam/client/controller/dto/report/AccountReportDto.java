package com.exam.client.controller.dto.report;

import com.exam.client.controller.dto.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AccountReportDto {

    private Long accountId;

    private AccountType accountType;

    private BigDecimal balance;

    private List<TransactionDto> transactions;

}
