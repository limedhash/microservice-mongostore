package com.sai.mongostore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
@EnableKafka
public class MongostoreApplication {


	public static void main(String[] args) {
		SpringApplication.run(MongostoreApplication.class, args);
	}
}
