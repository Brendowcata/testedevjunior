package com.nexti.teste.repository.impl;

import com.nexti.teste.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepositoryCustom {
    List<Person> getAllPerson();

    void savePerson(Person person);

    void updatePerson(Long id, Person person);

    void deletePerson(Long id);

    List<Person> getAllPersonActive();

    List<Person> getAllPersonInactive();

    void workplaceTransfer(Long id, Long workplaceId);

    List<Person> getPersonWorkingByWorkplace(Long id);

    List<Person> getPersonByWorkplace(Long id);

    Optional<Person> getById(Long id);
}
