package dev.vagnergrossklaus.simplespringrestcrud.web.rest;

import dev.vagnergrossklaus.simplespringrestcrud.domain.Person;
import dev.vagnergrossklaus.simplespringrestcrud.exception.ResourceNotFoundException;
import dev.vagnergrossklaus.simplespringrestcrud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PersonResource {

    @Autowired
    PersonService service;

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/person")
    public @ResponseBody
    ResponseEntity<List<Person>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/person")
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return new ResponseEntity<>(service.save(person), HttpStatus.CREATED);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> update(@PathVariable("id") Long id, @RequestBody Person person) {
        try{
            return ResponseEntity.ok().body(service.update(id, person));
        }catch(ResourceNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

}
