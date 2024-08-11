package com.exam.client.controller.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class AccountDto implements Serializable {

    @NotNull(message = "The account type can't be null!")
    private AccountType accountType;

    @NotNull(message = "The amount can't be null!")
    private BigDecimal amount;

    private Long idClient;

}
