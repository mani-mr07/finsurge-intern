package com.mongoExample.mongodbExample.repository;



import com.mongoExample.mongodbExample.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Student> listAllStudent(String name,int age){
        Query query=new Query();
        Criteria criteria=new Criteria();
        query.addCriteria(criteria.andOperator(Criteria.where("name").is(name),Criteria.where("age").gte(age)));
        return mongoTemplate.find(query,Student.class);
    }




}
