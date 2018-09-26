package com.nuhs.gcto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.nuhs.gcto.repository")
public class DiscoveryApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryApplication.class, args);
	}
}
