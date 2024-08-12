package com.exam.client.controller;

import com.exam.client.controller.dto.report.ReportDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportController {

    ReportDto createReport(LocalDateTime startDate, LocalDateTime endDate, Long idUser) throws JsonProcessingException;

    List<ReportDto> getAllReports();

    ReportDto getReport(Long id) throws JsonProcessingException;

}
