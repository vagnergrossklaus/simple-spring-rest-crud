package dev.vagnergrossklaus.simplespringrestcrud.repository;

import dev.vagnergrossklaus.simplespringrestcrud.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
