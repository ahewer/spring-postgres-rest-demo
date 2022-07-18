package com.example.springpostgresexample.model.repository;

import com.example.springpostgresexample.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  Person findByName(String familyName);

}
