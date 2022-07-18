package com.example.springpostgresexample.service;

import com.example.springpostgresexample.model.Address;
import com.example.springpostgresexample.model.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class PersonServiceTest {

    @Container
    static private final PostgreSQLContainer<? extends PostgreSQLContainer<?>> postgres =
        new PostgreSQLContainer<>(DockerImageName.parse("postgres"));

    @Autowired
    private PersonService service;
    @Autowired
    private AddressRepository addressRepository;

    @DynamicPropertySource
    static private void containerProperties(DynamicPropertyRegistry registry) {
      registry.add("spring.datasource.url", postgres::getJdbcUrl);
      registry.add("spring.datasource.username", postgres::getUsername);
      registry.add("spring.datasource.password", postgres::getPassword);
    }

  @Test
  public void testCreate() {
      service.createPerson("Test", "name");
  }

  @Test
  public void testGet() {
    service.createPerson("Test2", "nameTwo");
    var person = service.getPerson("Test2");
  }

  @Test
  public void testAddress() {
    service.createPerson("Test3", "nameTwo");
    var person = service.getPerson("Test3");
    Address addressWork = new Address();
    addressWork.setPersonId(person.getId());
    addressWork.setStreet("Test Street");

    Address addressHome = new Address();
    addressHome.setPersonId(person.getId());
    addressHome.setStreet("Test Work Street");

    service.addAddress(person, addressWork);
    service.addAddress(person, addressHome);
    var result = service.getAddressesOfPerson(person);

  }

}