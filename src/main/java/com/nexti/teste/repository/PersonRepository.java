package com.nexti.teste.repository;

import com.nexti.teste.domain.Person;
import com.nexti.teste.repository.impl.PersonRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>, PersonRepositoryCustom {
}
