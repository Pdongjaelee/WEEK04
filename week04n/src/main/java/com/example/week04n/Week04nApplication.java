package com.example.week04n;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //spring audit기능 활용
@SpringBootApplication
public class Week04nApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week04nApplication.class, args);
    }

}
