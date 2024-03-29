package com.microservicesjmp.songapp.songservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SongServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongServiceApplication.class, args);
	}

}
