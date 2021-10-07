package com.example.hcl.service;

import com.example.hcl.service.fibonacci.FibonacciRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@EnableAsync
@Component
//@RunWith(SpringRunner.class)
//@WebMvcTest(MasterController.class)
//@ContextConfiguration(classes={SpringBootApp.class})
public class FibonacciDriver {
    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciDriver.class);

    public static void main(String[] args) {
        SpringApplication.run(FibonacciDriver.class, args);
        LOGGER.info("Completed FibonacciDriver startup");
    }

    @Bean
//    @Scheduled
//    @Scheduled(cron = "* * * * * ?")
    public CommandLineRunner run(FibonacciRepository fibonacciRepository) {
        return (args) -> {
            System.out.println(fibonacciRepository.findAll());
        };
    }


}
