package com.OneToOne.Mapping.repository;

import com.OneToOne.Mapping.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.SequencedCollection;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query(value = "SELECT * from employee_userdetails WHERE employee_userdetails.name=name",nativeQuery = true)
    List<Employee> findByName(String name);

    @Query(name="Employee.findUserName")
    List<Employee>findUserName(String name);

    @Query(value="SELECT u from Employee u WHERE u.name=?1 AND u.role=?2")
    List<Employee>findAllById(String name,String role);

}
