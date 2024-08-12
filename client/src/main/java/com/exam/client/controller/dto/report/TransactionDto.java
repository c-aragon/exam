package com.exam.client.controller.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransactionDto {

    private LocalDateTime date;

    private BigDecimal amount;

    private BigDecimal balance;

    private BigDecimal oldBalance;

    private TransactionType transactionType;

    private StatusTransaction statusTransaction;
}
