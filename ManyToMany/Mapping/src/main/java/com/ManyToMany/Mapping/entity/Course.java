package com.ManyToMany.Mapping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    @ManyToMany(mappedBy = "courses")
    @Transient
    private List<Person> registeredPerson=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Person> getRegisteredPerson() {
        return registeredPerson;
    }

    public void setRegisteredPerson(List<Person> registeredPerson) {
        this.registeredPerson=registeredPerson;
    }
}
