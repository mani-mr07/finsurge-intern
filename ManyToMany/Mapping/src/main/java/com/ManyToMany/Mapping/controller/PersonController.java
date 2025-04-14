package com.ManyToMany.Mapping.controller;

import com.ManyToMany.Mapping.Service.PersonService;
import com.ManyToMany.Mapping.entity.Person;
import com.ManyToMany.Mapping.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @PostMapping
    public ResponseEntity createNewPerson(@RequestBody Person person){
        System.out.println(person.getName());
        return personService.createNewPerson(person);
    }
    @GetMapping
    public ResponseEntity getAllPerson(){
        return new ResponseEntity(personRepository.findAll(), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id){
        personRepository.deleteById(id);
        return new ResponseEntity(personRepository.findAll(),HttpStatus.OK);
    }
}
