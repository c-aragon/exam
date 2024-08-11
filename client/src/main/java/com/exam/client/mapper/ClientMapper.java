package com.exam.client.mapper;

import com.exam.client.controller.dto.ClientDto;
import com.exam.client.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto clientToClientDto(Client client);

    Client clientDtoToClient(ClientDto clientDto);

}
