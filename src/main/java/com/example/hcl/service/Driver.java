package com.example.hcl.service;

import com.example.hcl.service.fibonacci.FibonacciRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Driver {

    public static void main(String[] args) {
        SpringApplication.run(Driver.class, args);
    }
    @Bean
    public CommandLineRunner run(FibonacciRepository repository) {
        return (args) -> {
//            insertFourEmployees(repository);
            System.out.println("Hello World");
            System.out.println(repository.findAll());
        };
    }


}
