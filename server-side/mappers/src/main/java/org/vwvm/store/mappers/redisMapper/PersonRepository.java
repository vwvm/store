package org.vwvm.store.mappers.redisMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.vwvm.store.beans.redisBean.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, String> {
    List<Person> findByLastname(String lastname);

    Page<Person> findPersonByLastname(String lastname, Pageable page);

    List<Person> findByFirstnameAndLastname(String firstname, String lastname);

    List<Person> findByAddress_City(String city);

    List<Person> findByFamilyList_Username(String username);
}