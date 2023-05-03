package com.example.fiegerunner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class FiegeRunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiegeRunnerApplication.class, args);
    }

}
