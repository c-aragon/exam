package com.exam.account.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public Queue clientQueue(@Value("${queue.name.accounts}") String queueName) {
        return new Queue(queueName, false);
    }

    @Bean
    public Queue requestReportQueue(@Value("${queue.name.request-report}") String queueName) {
        return new Queue(queueName, false);
    }

    @Bean
    public Queue resultReportQueue(@Value("${queue.name.result-report}") String queueName) {
        return new Queue(queueName, false);
    }

}
