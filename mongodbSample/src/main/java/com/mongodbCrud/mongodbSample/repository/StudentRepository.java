package com.mongodbCrud.mongodbSample.repository;

import com.mongodbCrud.mongodbSample.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student,Integer> {
}
