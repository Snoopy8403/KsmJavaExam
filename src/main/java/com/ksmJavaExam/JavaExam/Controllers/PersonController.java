package com.ksmJavaExam.JavaExam.Controllers;

import com.ksmJavaExam.JavaExam.Models.Person;
import com.ksmJavaExam.JavaExam.Repositorys.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // GET - minden személy
    @GetMapping("")
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    // GET - egy adott személy
    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        return personRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - személy létrehozása
    @PostMapping("/create")
    public Person create(@RequestBody Person person) {
        return personRepository.save(person);
    }

    // PUT - személy szerkesztése
    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person updatedPerson) {
        return personRepository.findById(id)
                .map(data -> {
                    data.setName(updatedPerson.getName());
                    //data.setAddresses(updatedPerson.getAddresses());
                    //data.setContacts(updatedPerson.getContacts());
                    return ResponseEntity.ok(personRepository.save(data));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - személy törlése
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return personRepository.findById(id)
                .map(person -> {
                    personRepository.delete(person);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}