package com.java.springbatch.config;

import com.java.springbatch.Entity.Student;
import com.java.springbatch.Repository.StudentRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class StudentWriterProcessor implements ItemProcessor<Student,Student> {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student process(Student student) throws Exception {
        System.out.println("Processing student: " + student);
        return student;
    }
}
