package com.mongoExample.mongodbExample.repository;

import com.mongoExample.mongodbExample.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {
}
