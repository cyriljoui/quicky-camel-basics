package com.kiss.quicky.camel.basics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class CamelKafkaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CamelKafkaApplication.class, args);
	}

	@Autowired
	private Environment environment;

	@Override
	public void run(String... args) throws Exception {
		if (!environment.acceptsProfiles("test")) {
			Thread.currentThread().join();
		}
	}
}
