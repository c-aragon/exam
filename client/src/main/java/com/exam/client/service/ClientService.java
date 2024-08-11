package com.exam.client.service;

import com.exam.client.controller.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto createClient(ClientDto clientDto);

    ClientDto editClient(Long id, ClientDto clientDto);

    void deleteClient(Long id);

    ClientDto getClient(Long id);

    List<ClientDto> getClients();

}
