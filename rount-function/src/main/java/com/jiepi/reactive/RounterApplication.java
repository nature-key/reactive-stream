package com.jiepi.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class RounterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RounterApplication.class, args);
    }

}
