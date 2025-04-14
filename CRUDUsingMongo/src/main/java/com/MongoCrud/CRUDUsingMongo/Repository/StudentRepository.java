package com.MongoCrud.CRUDUsingMongo.Repository;

import com.MongoCrud.CRUDUsingMongo.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {
}
