package com.MongoCrud.CRUDUsingMongo.Controller;

import com.MongoCrud.CRUDUsingMongo.Model.Student;
import com.MongoCrud.CRUDUsingMongo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<List<Student>>getAllStudent(){
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.ACCEPTED);
    }
    @PostMapping
    public ResponseEntity<Student>createstudent(@RequestBody Student student){
        student.setId(UUID.randomUUID().toString().split("-")[0]);
        return new ResponseEntity<>(studentRepository.save(student),HttpStatus.CREATED);
    }
    @PutMapping("/{studentId}")
    public ResponseEntity<Student>updateStudent(@RequestBody Student student,@PathVariable String studentId)
    {
        System.out.println(studentId);
        Student student1=studentRepository.findById(studentId).get();
        System.out.println(student.getName());
        student1.setName(student.getName());
        student1.setAge(student.getAge());
        return new ResponseEntity<>(studentRepository.save(student1),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable String id) {
        Student student = studentRepository.findById(id).get();
    if(student!=null){
        studentRepository.deleteById(id);
        return "deleted";
    }
    else{
        return "Student not found";
    }
    }

}
