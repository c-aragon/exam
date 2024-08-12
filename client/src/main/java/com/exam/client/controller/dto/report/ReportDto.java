package com.exam.client.controller.dto.report;

import com.exam.client.model.ReportStatus;
import com.exam.client.service.dto.DataDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {

    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String name;

    private DataDto data;

    private Long idClient;

    private ReportStatus reportStatus;

}
