package com.exam.account.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDto implements Serializable {

    private Long idClient;

    private String name;

    private String address;

    private List<AccountReportDto> accounts;
}
