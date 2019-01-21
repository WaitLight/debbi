package org.dl.debbi.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DebbiUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(DebbiUserApplication.class, args);
	}
}
