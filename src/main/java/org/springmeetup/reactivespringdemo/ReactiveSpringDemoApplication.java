package org.springmeetup.reactivespringdemo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springmeetup.reactivespringdemo.data.Data;

@SpringBootApplication
public class ReactiveSpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveSpringDemoApplication.class, args);
	}
	
	@Bean
	public ApplicationRunner initialize(final ReactiveMongoTemplate mongoTemplate) {
		return args -> {
			Data.initializeAllData(mongoTemplate);
		};
	}
	
}
