package com.exam.client.controller;

import com.exam.client.controller.dto.AccountDto;
import com.exam.client.controller.dto.AccountType;
import com.exam.client.controller.dto.ClientDto;
import com.exam.client.model.Status;
import com.exam.client.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
public class ClientControllerUnitTest {

    @MockBean
    private ClientServiceImpl clientService;

    @Autowired
    private ClientController clientController;

    @Test
    public void createValidClient() {
        Mockito.when(clientService.createClient(ArgumentMatchers.any(ClientDto.class))).thenReturn(getClientDto());

        ClientDto clientDto = clientController.createClient(new ClientDto());

        Assertions.assertEquals(1L, clientDto.getId());
        Assertions.assertEquals(1, clientDto.getAccount().getIdClient());
        Assertions.assertEquals("12345678", clientDto.getPassword());
        Assertions.assertEquals(Status.ACTIVE, clientDto.getStatus());
        Assertions.assertEquals("Charly Brown", clientDto.getName());
        Assertions.assertEquals("MALE", clientDto.getGender());
        Assertions.assertEquals(LocalDate.of(1985, Month.JANUARY, 8), clientDto.getBirthDate());
        Assertions.assertEquals("CHBR909090HOCYTH07", clientDto.getIdCard());
        Assertions.assertEquals("5545554034", clientDto.getPhone());
        Assertions.assertEquals("Rio de la Plata 506", clientDto.getAddress());
    }

    private ClientDto getClientDto() {
        LocalDate localDate = LocalDate.of(1985, Month.JANUARY, 8);

        AccountDto accountDto = new AccountDto();
        accountDto.setIdClient(1L);
        accountDto.setAccountType(AccountType.AHORROS);
        accountDto.setAmount(new BigDecimal(100));

        ClientDto clientDto = new ClientDto();
        clientDto.setName("Charly Brown");
        clientDto.setAccount(accountDto);
        clientDto.setAddress("Rio de la Plata 506");
        clientDto.setId(1L);
        clientDto.setPhone("5545554034");
        clientDto.setGender("MALE");
        clientDto.setStatus(Status.ACTIVE);
        clientDto.setIdCard("CHBR909090HOCYTH07");
        clientDto.setBirthDate(localDate);
        clientDto.setPassword("12345678");
        return clientDto;
    }
}
