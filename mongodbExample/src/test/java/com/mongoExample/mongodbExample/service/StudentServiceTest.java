package com.mongoExample.mongodbExample.service;
import com.mongoExample.mongodbExample.entity.Address;
import com.mongoExample.mongodbExample.entity.Student;
import com.mongoExample.mongodbExample.repository.Addressrepository;
import com.mongoExample.mongodbExample.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private Addressrepository addressRepository;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStudent() {
        // Arrange
        Address address1 = new Address();
        address1.setAddressId("A1");
        address1.setStudentId("S1");
        address1.setStreetName("First Street");
        address1.setCityName("Chennai");

        Address address2 = new Address();
        address2.setAddressId("A2");
        address2.setStudentId("S1");
        address2.setStreetName("Second Street");
        address2.setCityName("Madurai");

        List<Address> addressList = Arrays.asList(address1, address2);

        Student student = new Student();
        student.setName("Mahe");
        student.setAge(22);
        student.setAddress("Tamil Nadu");
        student.setAddressList(addressList);

        long generatedRollNo = 101L;

        when(sequenceGeneratorService.generateSequence("student_sequence")).thenReturn(generatedRollNo);
        when(addressRepository.saveAll(addressList)).thenReturn(addressList);
        when(studentRepository.save(any(Student.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        Student result = studentService.addStudent(student);

        // Assert
        assertNotNull(result);
        assertEquals(generatedRollNo, result.getRollNo());
        assertEquals("Mahe", result.getName());
        assertEquals(2, result.getAddressList().size());

        verify(sequenceGeneratorService, times(1)).generateSequence("student_sequence");
        verify(addressRepository, times(1)).saveAll(addressList);
        verify(studentRepository, times(1)).save(result);
    }
    @Test
    void testfindByrollno(){
        Address address1 = new Address();
        address1.setAddressId("A1");
        address1.setStudentId("S1");
        address1.setStreetName("First Street");
        address1.setCityName("Chennai");

        Address address2 = new Address();
        address2.setAddressId("A2");
        address2.setStudentId("S1");
        address2.setStreetName("Second Street");
        address2.setCityName("Madurai");

        List<Address> addressList = Arrays.asList(address1, address2);

        Student student = new Student();
        student.setName("Mahe");
        student.setAge(22);
        student.setAddress("Tamil Nadu");
        student.setAddressList(addressList);

        long generatedRollNo = 101L;

        when(studentRepository.findByrollNo("101")).thenReturn(student);
        Student testStudent=studentService.findByrollno("101");
        assertNotNull(testStudent);
        assertEquals("Mahe",testStudent.getName());


    }
}
