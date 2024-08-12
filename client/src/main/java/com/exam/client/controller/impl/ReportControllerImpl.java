package com.exam.client.controller.impl;


import com.exam.client.controller.ReportController;
import com.exam.client.controller.dto.report.ReportDto;
import com.exam.client.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ReportControllerImpl implements ReportController {

    private final ReportService reportService;

    public ReportControllerImpl(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/report")
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ReportDto createReport(@RequestParam LocalDateTime startDate,
                                  @RequestParam LocalDateTime endDate,
                                  @RequestParam Long idClient) throws JsonProcessingException {
        return reportService.createReport(startDate, endDate, idClient);
    }

    @GetMapping("/report")
    @Override
    public List<ReportDto> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/report/{id}")
    @Override
    public ReportDto getReport(@PathVariable Long id) throws JsonProcessingException {
        return reportService.getReport(id);
    }

}
