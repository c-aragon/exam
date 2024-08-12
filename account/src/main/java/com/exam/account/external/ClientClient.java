package com.exam.account.external;

import com.exam.account.service.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "client", url = "${client.host}")
public interface ClientClient {

    @RequestMapping(method = RequestMethod.GET,
            value = "/clientes/{clientId}",
            produces = "application/json")
    ClientDto getClient(@PathVariable("clientId") Long clientId);


}
