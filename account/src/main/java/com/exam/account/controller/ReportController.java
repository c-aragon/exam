package com.exam.account.controller;

import com.exam.account.service.dto.DataDto;

import java.time.LocalDateTime;

public interface ReportController {

    DataDto createReport(LocalDateTime startDate, LocalDateTime endDate, Long idUser);


}
