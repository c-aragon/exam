package com.exam.client.external;

import com.exam.client.controller.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "account", url = "${account.host}")
public interface AccountClient {

    @RequestMapping(method = RequestMethod.GET,
            value = "/clientes/{clientId}",
            produces = "application/json")
    ClientDto getClient(@PathVariable("clientId") Long clientId);


}
