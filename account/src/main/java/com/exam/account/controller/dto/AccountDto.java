package com.exam.account.controller.dto;

import com.exam.account.model.AccountType;
import com.exam.account.model.StatusAccount;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDto implements Serializable {

    private Long id;

    @NotNull(message = "The account type can't be null!")
    private AccountType accountType;

    @NotNull(message = "The balance can't be null!")
    private BigDecimal balance;

    private BigDecimal amount;

    @NotNull(message = "The status account can't be null!")
    private StatusAccount statusAccount;

    @NotNull(message = "The id client can't be null!")
    private Long idClient;

    @NotNull(message = "The transaction can't be null!")
    private List<TransactionDto> transactions = new ArrayList<>();

}
