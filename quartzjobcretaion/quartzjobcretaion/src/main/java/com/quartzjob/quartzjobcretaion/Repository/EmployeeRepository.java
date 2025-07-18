package com.quartzjob.quartzjobcretaion.Repository;

import com.quartzjob.quartzjobcretaion.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    
    @Query("SELECT e from Employee e WHERE e.status IS NULL")
    List<Employee> getEmployeeWhereIdIsNull();

}
