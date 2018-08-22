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
        log.info("[XXXXXXXXXXXXXXX]___DEBBI_starting___[XXXXXXXXXXXXXXX]");
        SpringApplication.run(DebbiControllerApplication.class, args);
        log.info("[XXXXXXXXXXXXXXX]___DEBBI_started___[XXXXXXXXXXXXXXX]");
    }
}
