package com.mongoExample.mongodbExample.service;

import com.mongoExample.mongodbExample.entity.Address;
import com.mongoExample.mongodbExample.entity.Student;
import com.mongoExample.mongodbExample.repository.Addressrepository;
import com.mongoExample.mongodbExample.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private Addressrepository addressRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    public Student addStudent(Student student){
        student.setRollNo(sequenceGeneratorService.generateSequence("student_sequence"));
        List<Address> savedAddresses = addressRepository.saveAll(student.getAddressList());
        // Attach saved addresses to student
        student.setAddressList(savedAddresses);
        return studentRepository.save(student);
    }

    public Student findByrollno(String rollNo) {
        Student student=studentRepository.findByrollNo(rollNo);
        return student;
    }
}
