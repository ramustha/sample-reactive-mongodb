package com.example.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/persons")
public class PersonController {

  @Autowired
  private PersonRepository personRepository;

  @GetMapping
  public Flux<Person> findAll() {
    return personRepository.findAll().log();
  }

  @PutMapping(path = "/{id}")
  public Mono<Person> update(@PathVariable("id") String id,
      @Valid @RequestBody PersonUpdate updatePerson) {
    return personRepository.findById(id)
        .switchIfEmpty(Mono.error(new IllegalArgumentException("Person not found")))
        .map(person -> {
          person.setName(updatePerson.getName());
          person.setAge(updatePerson.getAge());
          return person;
        })
        .flatMap(person -> personRepository.save(person))
        .log();
  }
}
