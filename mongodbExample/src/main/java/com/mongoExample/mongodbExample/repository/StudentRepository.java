package com.mongoExample.mongodbExample.repository;

import com.mongoExample.mongodbExample.entity.Address;
import com.mongoExample.mongodbExample.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student,Integer> {
    Student findByrollNo(String rollNo);
    void deleteByrollNo(String rollNo);
    @Query("{'address':?0}")
    List<Student> findStudentByAddress(String address);
    @Query("{'$or':[{'address':?0},{'name':?1}]}")
    List<Student>findStudentByNameAndAddress(String address,String name);
    @Query("{'age':{$gt:?0}}")
    List<Student>findStudentWhoseAgeIsGreater(int age);
    Page<Student>findAll(Pageable pageable);
    Student findByAddressListContains(Address address);

}
