package com.exam.account.service.impl;

import com.exam.account.model.Account;
import com.exam.account.model.AccountType;
import com.exam.account.model.StatusAccount;
import com.exam.account.model.Transaction;
import com.exam.account.repository.AccountRepository;
import com.exam.account.repository.TransactionRepository;
import com.exam.account.service.AccountService;
import com.exam.account.service.consumer.ConsumerAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 9999)
public class ConsumerAccountServiceTest {

    @MockBean
    private RabbitTemplate template;

    @Autowired
    private ConsumerAccountService consumerAccountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void receiveMessageForCreateAccount() throws JsonProcessingException {
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();

        WireMock.configureFor("localhost", 9999);

        WireMock.stubFor(
                WireMock.get(WireMock.urlEqualTo("/clientes/4"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":4}")));

        consumerAccountService.listen("{\"accountType\":\"AHORROS\",\"amount\":500.0,\"idClient\":4}");

        Account account = accountRepository.findById(5L).get();

        Transaction transaction = transactionRepository.findById(1L).get();

        Assertions.assertEquals(4L, account.getIdClient());
        Assertions.assertEquals(AccountType.AHORROS, account.getAccountType());
        Assertions.assertEquals(StatusAccount.ACTIVE, account.getStatusAccount());

        Assertions.assertEquals(new BigDecimal("500.0"), transaction.getBalance());
        Assertions.assertEquals(new BigDecimal(0), transaction.getOldBalance());
        Assertions.assertEquals(5L, transaction.getAccount().getId());
    }

}
