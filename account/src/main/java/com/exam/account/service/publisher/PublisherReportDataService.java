package com.exam.account.service.publisher;

import com.exam.account.service.dto.DataDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PublisherReportDataService {

    private final String queueName;

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public PublisherReportDataService(RabbitTemplate rabbitTemplate,
                                      ObjectMapper objectMapper,
                                      @Value("${queue.name.result-report}") String queueName) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    public void send(DataDto data) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(queueName, objectMapper.writeValueAsString(data));
    }

}
