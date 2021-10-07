package com.example.hcl.service;

import com.example.hcl.service.fibonacci.FibonacciRepository;
import com.example.hcl.service.prime.PrimeRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootApplication
//@EnableAsync

@RunWith(SpringRunner.class)
//@WebMvcTest(MasterController.class)
//@ContextConfiguration(classes={SpringBootApp.class})
public class Driver {

    public static void main(String[] args) {
        SpringApplication.run(Driver.class, args);
    }

    //    @Bean
//    @Scheduled
    @Scheduled(cron = "* * * * * ?")
    public CommandLineRunner run(FibonacciRepository fibonacciRepository,
                                 PrimeRepository primeRepository) {
        return (args) -> {
//            insertFourEmployees(repository);
            System.out.println("Hello World");
            System.out.println(fibonacciRepository.findAll());
            System.out.println(primeRepository.findAll());
        };
    }


}
