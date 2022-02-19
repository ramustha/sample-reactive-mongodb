package com.example.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.List;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class SampleReactiveMongodbApplication implements ApplicationRunner {

  public static void main(String[] args) {
    SpringApplication.run(SampleReactiveMongodbApplication.class, args);
  }

  @Autowired
  private PersonRepository repository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    repository.saveAll(List.of(new Person("Jack", 21),
        new Person("Richard", 22),
        new Person("Tom", 23))).subscribe();
  }
}
