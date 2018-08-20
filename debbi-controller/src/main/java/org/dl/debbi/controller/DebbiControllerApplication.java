package org.dl.debbi.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.dl.debbi"})
public class DebbiControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DebbiControllerApplication.class, args);
    }
}
