package com.exam.account.controller.impl;

import com.exam.account.controller.ReportController;
import com.exam.account.service.ReportService;
import com.exam.account.service.dto.DataDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ReportControllerImpl implements ReportController {

    private final ReportService reportService;

    public ReportControllerImpl(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/report")
    @Override
    public DataDto createReport(@RequestParam LocalDateTime startDate,
                                @RequestParam LocalDateTime endDate,
                                @RequestParam Long idClient) {
        return reportService.createReport(startDate, endDate, idClient);
    }

}
