package br.com.locaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "br.com.locaweb.repository")
public class LocawebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocawebApplication.class, args);
	}

}
