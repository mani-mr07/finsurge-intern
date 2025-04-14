package com.mongoExample.mongodbExample.controller;

import com.mongoExample.mongodbExample.entity.Address;
import com.mongoExample.mongodbExample.entity.Department;
import com.mongoExample.mongodbExample.entity.Student;
import com.mongoExample.mongodbExample.repository.Addressrepository;
import com.mongoExample.mongodbExample.repository.DepartmentRepository;
import com.mongoExample.mongodbExample.repository.RepositoryImpl;
import com.mongoExample.mongodbExample.repository.StudentRepository;
import com.mongoExample.mongodbExample.service.SequenceGeneratorService;
import com.mongoExample.mongodbExample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.mongodb.core.aggregation.Aggregation;


import java.util.List;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RepositoryImpl repository;
    @Autowired
    private Addressrepository addressRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private StudentService studentService;
    @PostMapping("/addStudent")
    public ResponseEntity<?>addStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
    }
    @GetMapping("/getOneStudent/{rollNo}")
    public ResponseEntity<?>getStudentById(@PathVariable String rollNo){
        Student student=studentService.findByrollno(rollNo);
        if(student!=null){
        return new ResponseEntity<>(student,HttpStatus.OK);}
        else{
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/getAllStudents")
    public ResponseEntity<?>getAllStudent(){
        return new ResponseEntity<>(studentRepository.findAll(),HttpStatus.OK);
    }
    @PutMapping("/update/{rollNo}")
    public ResponseEntity<?>updateStudent(@PathVariable String rollNo,@RequestBody Student student){
        Student student1=studentRepository.findByrollNo(rollNo);
        student1.setAge(student.getAge());
        student1.setName(student1.getName());
        return new ResponseEntity<>(studentRepository.save(student1),HttpStatus.CREATED);
    }
    @PatchMapping("/update/{rollNo}")
    public ResponseEntity<?>particularUpdateStudent(@PathVariable String rollNo,@RequestBody Student student){

        Student student1= studentRepository.findByrollNo(rollNo);
        if(student.getAge()!=0){
            student1.setAge(student.getAge());
        }
        if(student.getName()!=null){
            student1.setName(student.getName());
        }
       return new ResponseEntity<>(studentRepository.save(student1),HttpStatus.OK);
    }
    @DeleteMapping("delete/{rollNo}")
    public ResponseEntity<?>deleteStudent(@PathVariable String rollNo){
        studentRepository.deleteByrollNo(rollNo);
        return new ResponseEntity<>("The student is deleted",HttpStatus.OK);
    }
    @GetMapping("getAll/{address}")
    public ResponseEntity<?>getAllStudent(@PathVariable String address){
       List<Student> studentList= studentRepository.findStudentByAddress(address);
       return new ResponseEntity<>(studentList,HttpStatus.OK);
    }
    @GetMapping("getAll/{address}/{name}")
    public ResponseEntity<?>getAllStudent(@PathVariable String address,@PathVariable String name){
       List<Student>studentList=studentRepository.findStudentByNameAndAddress(address,name);
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }
    @GetMapping("age/{age}")
    public ResponseEntity<?>getStudent(@PathVariable int age){
        List<Student>studentList=studentRepository.findStudentWhoseAgeIsGreater(age);
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }
    @GetMapping("paging/{pageNo}/{pageSize}")
    public ResponseEntity<?>getAllStudent(@PathVariable int pageNo,@PathVariable int pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        Page<Student>pages=studentRepository.findAll(pageable);
        return new ResponseEntity<>(pages,HttpStatus.OK);
    }
    @GetMapping("{name}/{age}")
    public ResponseEntity<?>getStudentByFiltration(@PathVariable String name,@PathVariable int age){
        return new ResponseEntity<>(repository.listAllStudent(name,age),HttpStatus.OK);
    }
    @GetMapping("find/{cityName}")
    public ResponseEntity<?>getStudentByCityName(@PathVariable String cityName){
        Address address=addressRepository.findByCityName(cityName);
        String addressid=address.getAddressId();
        Student  student=studentRepository.findByAddressListContains(address);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }
    @PostMapping("createDepartment")
    public ResponseEntity<?>createDepartment(@RequestBody Department department){
     return new ResponseEntity<>(departmentRepository.save(department),HttpStatus.OK);
    }
    @GetMapping("project/{fieldName}")
    public ResponseEntity<?>getProjectedFields(@PathVariable String fieldName){
        Aggregation aggregation= Aggregation.newAggregation(
                project( "name"));

        AggregationResults<Student> results = mongoTemplate.aggregate(aggregation, "student", Student.class);
        results.getMappedResults().forEach(System.out::println);
        return new ResponseEntity<>(results.getMappedResults(),HttpStatus.OK);
    }
    @GetMapping("/lookUp")
    public ResponseEntity<?>getStudentByLookUp(){
        Aggregation aggregation=Aggregation.newAggregation(Aggregation.lookup("address","_id","studentId","addressList"));
        return new ResponseEntity<>(mongoTemplate.aggregate(aggregation,"student",Student.class).getMappedResults(),HttpStatus.OK);
    }
}
