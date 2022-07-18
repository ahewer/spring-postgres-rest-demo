package com.example.springpostgresexample.service;

import com.example.springpostgresexample.model.Address;
import com.example.springpostgresexample.model.Person;
import com.example.springpostgresexample.model.repository.AddressRepository;
import com.example.springpostgresexample.model.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

  private final PersonRepository personRepository;
  private final AddressRepository addressRepository;

  public PersonService(PersonRepository personRepository, AddressRepository addressRepository) {
    this.personRepository = personRepository;
    this.addressRepository = addressRepository;
  }

  public void createPerson(String name, String familyName) {
    Person person = new Person();
    person.setFamilyName(familyName);
    person.setName(name);
    personRepository.save(person);
  }

  public Person getPerson(String name) {
    return personRepository.findByName(name);
  }

  public void addAddress(Person person, Address address) {
    address.setPersonId(person.getId());
    addressRepository.save(address);
  }

  public List<Address> getAddressesOfPerson(Person person) {
    return addressRepository.findByPersonId(person.getId());
  }

  public List<Person> getAllPersons() {
    return personRepository.findAll();
  }
}
