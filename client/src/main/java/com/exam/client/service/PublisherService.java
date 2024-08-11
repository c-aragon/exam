package com.exam.client.service;

import com.exam.client.controller.dto.AccountDto;
import com.exam.client.mapper.AccountMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PublisherService {

    private final String queueName;

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public PublisherService(final RabbitTemplate rabbitTemplate,
                            ObjectMapper objectMapper,
                            @Value("${queue.name}") final String queueName) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
        this.objectMapper = objectMapper;
    }

    public void send(AccountDto account) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(queueName, objectMapper.writeValueAsString(account));
    }

}
