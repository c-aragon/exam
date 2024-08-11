package com.exam.client.controller;

import com.exam.client.controller.dto.ClientDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getFirstClient() throws Exception {
        ClientDto clientDto = this.restTemplate.getForObject("http://localhost:" + port + "/clientes/1", ClientDto.class);

        Assertions.assertEquals("Jose Lema", clientDto.getName());
        Assertions.assertEquals("MALE", clientDto.getGender());
        Assertions.assertEquals("JOLE851128HOCRRR09", clientDto.getIdCard());
        Assertions.assertEquals(1L, clientDto.getId());
    }
}
