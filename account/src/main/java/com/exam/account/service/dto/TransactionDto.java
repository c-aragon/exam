package com.exam.account.service.dto;

import com.exam.account.model.StatusTransaction;
import com.exam.account.model.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransactionDto {

    private LocalDateTime date;

    private BigDecimal amount;

    private BigDecimal balance;

    private BigDecimal oldBalance;

    private TransactionType transactionType;

    private StatusTransaction statusTransaction;
}
