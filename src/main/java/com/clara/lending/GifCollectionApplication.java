package com.clara.lending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.clara.*"})
public class GifCollectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GifCollectionApplication.class, args);
	}
}
