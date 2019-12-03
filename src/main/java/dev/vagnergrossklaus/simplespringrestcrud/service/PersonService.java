package dev.vagnergrossklaus.simplespringrestcrud.service;

import dev.vagnergrossklaus.simplespringrestcrud.domain.Person;
import dev.vagnergrossklaus.simplespringrestcrud.exception.ResourceNotFoundException;
import dev.vagnergrossklaus.simplespringrestcrud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public Person findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        repository.findAll().forEach(persons::add);
        return persons;
    }

    public Person save(Person person) {
        return repository.save(person);
    }

    public Person update(Long id, Person person) throws ResourceNotFoundException {
        return repository.findById(id).map(record -> {
            record.setName(person.getName());
            return repository.save(record);
        }).orElseThrow(() -> new ResourceNotFoundException("Pessoa código " + id));
    }

}
