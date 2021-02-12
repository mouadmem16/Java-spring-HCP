package com.example.accountoperationservice.service;

import com.example.accountoperationservice.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="client-service", url="http://localhost:8081")
public interface ClientServiceClient {
    @GetMapping("/clients/{id}?projection=fullClient")
    Client findClientById(@PathVariable("id") Long id);
    /*@GetMapping("/products?projection=fullProduct")
    PagedModel<Client> findAll();*/
}
