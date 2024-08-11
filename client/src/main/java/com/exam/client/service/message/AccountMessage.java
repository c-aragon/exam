package com.exam.client.service.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
public class AccountMessage implements Serializable {

    private Integer accountType;

    private BigDecimal balance;

    private Long idClient;

}
