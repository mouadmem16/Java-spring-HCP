package com.rad.clientservice;

import com.rad.clientservice.entities.Client;
import com.rad.clientservice.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientRepository clientRepository){
        return args -> {
            clientRepository.save(new Client(null,"RADOUANI Yasser","y.radouani929@gmail.com"));
            clientRepository.save(new Client(null,"ELWADI Taha","taha@gmail.com"));
            clientRepository.save(new Client(null,"ELHAYANI Walid","walid@gmail.com"));
            clientRepository.findAll().forEach(System.out::println);
        };
    }
}
