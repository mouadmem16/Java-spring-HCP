package com.example.accountoperationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.accountoperationservice.service")
public class AccountOperationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountOperationServiceApplication.class, args);
    }

}
