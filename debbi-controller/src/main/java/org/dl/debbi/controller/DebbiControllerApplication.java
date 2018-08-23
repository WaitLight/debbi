package org.dl.debbi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.dl.debbi"})
@Slf4j
public class DebbiControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DebbiControllerApplication.class, args);
        log.info("[XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX]___DEBBI_started___[XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX]");
    }
}
