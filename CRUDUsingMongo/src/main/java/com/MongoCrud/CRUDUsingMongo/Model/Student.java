package com.MongoCrud.CRUDUsingMongo.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Student")
@Data
@AllArgsConstructor
public class Student {

    @Id
    private String id;
    private String name;
    private int age;
}
