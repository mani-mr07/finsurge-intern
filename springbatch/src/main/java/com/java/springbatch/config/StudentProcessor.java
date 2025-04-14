package com.java.springbatch.config;

import com.java.springbatch.Entity.Student;
import com.java.springbatch.Repository.StudentRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class StudentProcessor implements ItemProcessor<Student,Student> {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student process(Student student) throws Exception {

        System.out.println("Processing student: " + student);
        Optional<Student> existingStudent=studentRepository.findById(student.getId());
        if(existingStudent.isPresent()){
            System.out.println("Duplicate Student");
            return null;
        }
        if(student.getAge()>21){
        student.setEligibilityForGate(true);}
        else{
            student.setEligibilityForGate(false);
        }
        if(student.getId()<500){
            student.setStudentIdType(false);
        }
        else{
            student.setStudentIdType(true);
        }
        return student;
    }
}
