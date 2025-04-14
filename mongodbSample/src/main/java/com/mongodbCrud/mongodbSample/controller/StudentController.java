package com.mongodbCrud.mongodbSample.controller;

import com.mongodbCrud.mongodbSample.entity.Student;
import com.mongodbCrud.mongodbSample.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @PostMapping("/createStudent")
    public ResponseEntity<?>createStudent(@RequestBody Student student){
       return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
    }
}
