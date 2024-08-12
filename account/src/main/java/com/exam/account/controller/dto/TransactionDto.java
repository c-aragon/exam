package com.exam.account.controller.dto;

import com.exam.account.model.StatusTransaction;
import com.exam.account.model.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private Long id;

    @NotNull(message = "The account can't be null!")
    private Long accountId;

    private LocalDateTime date;

    @NotNull(message = "The amount can't be null!")
    private BigDecimal amount;

    private BigDecimal balance;

    private BigDecimal oldBalance;

    private StatusTransaction statusTransaction;

    private TransactionType transactionType;

}
