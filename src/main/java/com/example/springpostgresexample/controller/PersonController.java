package com.example.springpostgresexample.controller;

import com.example.springpostgresexample.model.Person;
import com.example.springpostgresexample.service.PersonService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

  private final PersonService service;

  public PersonController(PersonService service) {
    this.service = service;
  }

  @PostMapping("/person/add")
  public void add(
    @Parameter(description = "Person name.")
    @RequestParam String name,
    @Parameter(description = "Person family name.")
    @RequestParam String familyName
  ) {
    service.createPerson(name, familyName);
  }

  @GetMapping("/person/get")
  public List<Person> get() {
    return service.getAllPersons();
  }
}
