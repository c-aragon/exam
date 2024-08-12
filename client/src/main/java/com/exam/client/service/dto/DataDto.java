package com.exam.client.service.dto;

import com.exam.client.controller.dto.report.AccountReportDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDto {

    private Long id;

    private Long idClient;

    private List<AccountReportDto> accounts;
}
