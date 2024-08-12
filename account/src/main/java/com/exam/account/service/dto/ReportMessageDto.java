package com.exam.account.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReportMessageDto {

    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long idClient;

}
