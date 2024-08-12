package com.exam.client.service.consumer;

import com.exam.client.exception.EntityNotFoundException;
import com.exam.client.model.Report;
import com.exam.client.model.ReportStatus;
import com.exam.client.repository.ReportRepository;
import com.exam.client.service.dto.DataDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsumerReportDataService {

    private final ObjectMapper objectMapper;

    private final ReportRepository reportRepository;

    public ConsumerReportDataService(ReportRepository reportRepository,
                                     ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.reportRepository = reportRepository;
    }

    @RabbitListener(queues = "result-report")
    public void listen(String result) throws JsonProcessingException {
        DataDto accountDto = objectMapper.readValue(result, DataDto.class);

        Optional<Report> report = reportRepository.findById(accountDto.getId());

        if (report.isPresent()) {
            report.get().setData(result);
            report.get().setReportStatus(ReportStatus.FINISH);
            reportRepository.save(report.get());
        } else {
            throw new EntityNotFoundException(String.format("Report %d doesn't exists!", accountDto.getId()));
        }

    }

}
