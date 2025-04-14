package com.java.springbatch.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Student_INFO")

public class Student {

    @Id
    @Column(name="STUDENT_ID")
    private Long id;
    @Column(name="STUDENT_NAME")
    private String name;
    @Column(name="STUDENT_EMAIL")
    private String email;
    @Column(name="STUDENT_AGE")
    private int age;
    @Column(name="STUDENT_ADDRESS")
    private String address;
    @Column(name="STUDENT_ELIGIBILITY")
    private boolean eligibilityForGate;
    @Column(name="STUDENT_ID_VALIDATION")
    private boolean studentIdType;

    public Student(){}

    public Student(Long id, String name, String email, int age, String address,boolean eligibilityForGate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
        this.eligibilityForGate=eligibilityForGate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEligibilityForGate() {
        return eligibilityForGate;
    }

    public void setEligibilityForGate(boolean eligibilityForGate) {
        this.eligibilityForGate = eligibilityForGate;
    }

    public boolean isStudentIdType() {
        return studentIdType;
    }

    public void setStudentIdType(boolean studentIdType) {
        this.studentIdType = studentIdType;
    }
}
