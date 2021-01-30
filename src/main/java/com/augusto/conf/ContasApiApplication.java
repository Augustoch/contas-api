package com.augusto.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.augusto")
@EnableJpaRepositories("com.augusto.repository")
@EntityScan("com.augusto.model")
@SpringBootApplication
public class ContasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContasApiApplication.class, args);
	}
	
}
