package com.OneToMany.Mapping.controller;

import com.OneToMany.Mapping.Repository.StudentRepository;

import com.OneToMany.Mapping.entity.Student;
import com.OneToMany.Mapping.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    @PostMapping
    public ResponseEntity<Student>createStudent(@RequestBody Student student){
        System.out.println(student.getName());
        System.out.println(student.getAddresses());
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>>getAllStudent(){
        return new ResponseEntity<>(studentRepository.findAll(),HttpStatus.ACCEPTED);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Student>updateStudent(@PathVariable Long id,@RequestBody Student student){
        return studentService.updateStudent(id,student);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<List<Student>>deleteStudent(@PathVariable Long id){
        studentRepository.deleteById(id);
        List<Student>students=studentRepository.findAll();
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

}
