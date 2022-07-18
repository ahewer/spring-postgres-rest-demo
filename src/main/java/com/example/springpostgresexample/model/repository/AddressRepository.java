package com.example.springpostgresexample.model.repository;

import com.example.springpostgresexample.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

  @Query(value = "SELECT address.id, address.person_id, address.street " +
      " FROM address JOIN person AS person ON address.person_id = person.id" +
      " WHERE person.name = ?1 AND person.familyname = ?2", nativeQuery = true)
  List<Address> findByPerson(String personName, String personFamilyName);

  List<Address> findByPersonId(Long personId);
}
