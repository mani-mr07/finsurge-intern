package com.OneToOne.Mapping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Employee_Userdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
        name = "Employee.findUserName",
        query = "SELECT u FROM Employee u WHERE u.name = :name"
)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "employee")
    private EmpId empId;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public EmpId getEmpId() {
        return empId;
    }

    public void setEmpId(EmpId empId) {
        this.empId = empId;
    }
}


