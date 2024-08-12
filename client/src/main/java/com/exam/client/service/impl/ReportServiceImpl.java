package com.exam.client.service.impl;

import com.exam.client.controller.dto.ClientDto;
import com.exam.client.controller.dto.report.ReportDto;
import com.exam.client.exception.EntityNotFoundException;
import com.exam.client.model.Report;
import com.exam.client.model.ReportStatus;
import com.exam.client.repository.ReportRepository;
import com.exam.client.repository.ReportWithoutDataRepository;
import com.exam.client.service.ClientService;
import com.exam.client.service.ReportService;
import com.exam.client.service.dto.DataDto;
import com.exam.client.service.publisher.PublisherReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    private final ReportWithoutDataRepository reportWithoutDataRepository;

    private final ClientService clientService;

    private final ObjectMapper objectMapper;

    private final PublisherReportService publisherReportService;

    public ReportServiceImpl(ReportRepository reportRepository,
                             ReportWithoutDataRepository reportWithoutDataRepository,
                             ClientService clientService,
                             PublisherReportService publisherReportService,
                             ObjectMapper objectMapper) {
        this.reportRepository = reportRepository;
        this.reportWithoutDataRepository = reportWithoutDataRepository;
        this.clientService = clientService;
        this.publisherReportService = publisherReportService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ReportDto createReport(LocalDateTime startDate, LocalDateTime endDate, Long idClient) {
        ClientDto clientDto = clientService.getClient(idClient);

        ReportDto reportDto = new ReportDto();
        reportDto.setName(clientDto.getName());
        reportDto.setStartDate(startDate);
        reportDto.setEndDate(endDate);
        reportDto.setData(null);
        reportDto.setIdClient(idClient);
        reportDto.setReportStatus(ReportStatus.IN_PROGRESS);

        Report report = new Report();
        report.setName(clientDto.getName());
        report.setEndDate(endDate);
        report.setIdClient(idClient);
        report.setStartDate(startDate);
        report.setReportStatus(ReportStatus.IN_PROGRESS);
        report.setData(null);

        reportRepository.save(report);

        reportDto.setId(report.getId());

        /*publisherReportService.send(ReportMessageDto.builder()
                        .id(report.getId())
                        .startDate(report.getStartDate())
                        .endDate(report.getEndDate())
                        .idClient(report.getIdClient())
                .build());*/

        return reportDto;
    }

    @Override
    public List<ReportDto> getAllReports() {
        return reportWithoutDataRepository.findAll().stream().map(report -> ReportDto.builder()
                .id(report.getId())
                .idClient(report.getIdClient())
                .reportStatus(report.getReportStatus())
                .startDate(report.getStartDate())
                .endDate(report.getEndDate())
                .name(report.getName())
                .build()).toList();
    }

    @Override
    public ReportDto getReport(Long id) throws JsonProcessingException {
        Optional<Report> report = reportRepository.findById(id);
        if (report.isEmpty()) {
            throw new EntityNotFoundException(String.format("Report %d not found!", id));
        }
        return ReportDto.builder()
                .id(report.get().getId())
                .reportStatus(report.get().getReportStatus())
                .idClient(report.get().getIdClient())
                .name(report.get().getName())
                .startDate(report.get().getStartDate())
                .endDate(report.get().getEndDate())
                .data(objectMapper.readValue(report.get().getData(), DataDto.class))
                .build();
    }
}
