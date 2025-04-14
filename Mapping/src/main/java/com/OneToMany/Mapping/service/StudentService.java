package com.OneToMany.Mapping.service;

import com.OneToMany.Mapping.Repository.StudentRepository;
import com.OneToMany.Mapping.entity.Address;
import com.OneToMany.Mapping.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<Student>updateStudent(Long id,Student student){
        Student student1=studentRepository.findById(id).get();
        student1.setName(student.getName());
        for(Address address:student1.getAddresses()){
            address.setStudent(null);
        }
        student1.setAddresses(student.getAddresses());
        return new ResponseEntity<>(studentRepository.save(student1), HttpStatus.CREATED);
    }

}
