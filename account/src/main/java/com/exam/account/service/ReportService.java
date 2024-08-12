package com.exam.account.service;

import com.exam.account.service.dto.DataDto;

import java.time.LocalDateTime;

public interface ReportService {

    DataDto createReport(LocalDateTime startDate, LocalDateTime endDate, Long idUser);

}
