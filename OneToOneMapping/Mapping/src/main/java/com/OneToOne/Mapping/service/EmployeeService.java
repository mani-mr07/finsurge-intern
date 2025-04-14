package com.OneToOne.Mapping.service;

import com.OneToOne.Mapping.entity.CustomerDetails;
import com.OneToOne.Mapping.entity.Employee;
import com.OneToOne.Mapping.repository.CustomerDetailsRepository;
import com.OneToOne.Mapping.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.SequencedCollection;

@Service
public class EmployeeService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;

    public Employee create(Employee employee){
        System.out.println(employee.getEmpId().getEmpId());
        employee.getEmpId().setEmployee(employee);
        return employeeRepository.save(employee);
    }
    public List<Employee> findByName(String name) {
        System.out.println("Inside the Service for the user "+name);
        TypedQuery<Employee> query = (TypedQuery<Employee>) entityManager.createQuery("SELECT u FROM Employee u WHERE u.name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    public HashMap<Object,HttpStatusCode> createNewCustomer(CustomerDetails customerDetails) {
      HashMap<Object,HttpStatusCode>map=new HashMap<>();
        if(customerDetails.getName()!=null){
            HttpStatusCode httpStatusCode= HttpStatus.CREATED;
            map.put(customerDetailsRepository.save(customerDetails),httpStatusCode);
        }
        else{
            map.put(null,HttpStatus.BAD_REQUEST);
        }
        return map;
    }
}
