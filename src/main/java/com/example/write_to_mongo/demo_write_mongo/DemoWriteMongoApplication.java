package com.example.write_to_mongo.demo_write_mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.write_to_mongo.Repository.StudentRepository;

@EnableMongoRepositories(basePackageClasses = StudentRepository.class)
@ComponentScan({"com.example.write_to_mongo.Controller","com.example.write_to_mongo.Service","com.example.write_to_mongo.Repository","com.example.write_to_mongo.Repository.Impl","com.example.write_to_mongo.Config"})
@SpringBootApplication
public class DemoWriteMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWriteMongoApplication.class, args);
	}

}
