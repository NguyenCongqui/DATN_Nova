package com.example.duantotnghiepgiaythethaonova;

import com.example.duantotnghiepgiaythethaonova.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableScheduling
public class DuantotnghiepgiaythethaonovaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuantotnghiepgiaythethaonovaApplication.class, args);
    }
//    @Bean
//    CommandLineRunner init(StorageService storageService) {
//        return (args ->{
//            storageService.init();
//        });
//    }

}
