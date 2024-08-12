package com.exam.client.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportMessageDto implements Serializable {

    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long idClient;

}
