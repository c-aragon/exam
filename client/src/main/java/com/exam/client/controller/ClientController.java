package com.exam.client.controller;

import com.exam.client.controller.dto.ClientDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface ClientController {

    ClientDto createClient(ClientDto clientDto);

    ClientDto editClient(Long id, ClientDto clientDto);

    void deleteClient(Long id);

    ClientDto getClient(Long id);

    List<ClientDto> getClients();
}
