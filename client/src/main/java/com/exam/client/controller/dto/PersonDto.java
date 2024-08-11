package com.exam.client.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PersonDto {

    @NotNull(message = "The name can't be null!")
    @NotBlank(message = "The name can't be blank!")
    private String name;

    @NotNull(message = "The gender can't be null!")
    @NotBlank(message = "The gender can't be blank!")
    private String gender;

    @NotNull(message = "The birth date can't be null!")
    private LocalDate birthDate;

    @NotNull(message = "The id Card can't be null!")
    @NotBlank(message = "The id Card can't be blank!")
    private String idCard;

    @NotNull(message = "The phone can't be null!")
    @NotBlank(message = "The phone can't be blank!")
    private String phone;

    @NotNull(message = "The address can't be null!")
    @NotBlank(message = "The address can't be blank!")
    private String address;

}
