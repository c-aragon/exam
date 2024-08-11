package com.exam.client.service.impl;

import com.exam.client.controller.dto.ClientDto;
import com.exam.client.exception.EntityNotFoundException;
import com.exam.client.mapper.ClientMapper;
import com.exam.client.model.Client;
import com.exam.client.repository.ClientRepository;
import com.exam.client.service.ClientService;
import com.exam.client.service.PublisherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final PublisherService publisherService;

    public ClientServiceImpl(ClientRepository clientRepository,
                             ClientMapper clientMapper,
                             PublisherService publisherService) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.publisherService = publisherService;
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client client = clientMapper.clientDtoToClient(clientDto);
        try {
            clientRepository.save(client);
            clientDto.getAccount().setIdClient(client.getId());
            publisherService.send(clientDto.getAccount());
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(ex.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public ClientDto editClient(Long id, ClientDto clientDto) {
        Client client = getClientEntity(id);
        client.setPassword(clientDto.getPassword());
        client.setStatus(clientDto.getStatus());
        client.setAddress(client.getAddress());
        client.setBirthDate(clientDto.getBirthDate());
        client.setPhone(clientDto.getPhone());
        client.setId(id);
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public void deleteClient(Long id) {
        Client client = getClientEntity(id);
        clientRepository.delete(client);
    }

    @Override
    public ClientDto getClient(Long id) {
        Client client = getClientEntity(id);
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public List<ClientDto> getClients() {
        return clientRepository.findAll().stream().map(clientMapper::clientToClientDto).toList();
    }

    public Client getClientEntity(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        }
        throw new EntityNotFoundException(String.format("Client %d doesn't exists!", id));
    }
}
