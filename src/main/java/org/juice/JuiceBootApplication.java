package org.juice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JuiceBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JuiceBootApplication.class, args);
        log.info("JuiceBootApplication started successfully.");
        log.info("http://localhost:8080/");
    }

}
