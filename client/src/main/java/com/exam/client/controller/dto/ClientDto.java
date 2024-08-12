package com.exam.client.controller.dto;

import com.exam.client.model.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ClientDto extends PersonDto {

    private Long id;

    @Size(message = "The password must not be less than 10 characters", min = 5)
    private String password;

    @NotNull(message = "The name can't be null!")
    private Status status;

    private AccountDto account;

}
