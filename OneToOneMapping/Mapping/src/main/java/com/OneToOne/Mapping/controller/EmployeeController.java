package com.OneToOne.Mapping.controller;

import com.OneToOne.Mapping.entity.CustomerDetails;
import com.OneToOne.Mapping.entity.Employee;
import com.OneToOne.Mapping.repository.CustomerDetailsRepository;
import com.OneToOne.Mapping.repository.EmployeeRepository;
import com.OneToOne.Mapping.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.SequencedCollection;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;

    @Autowired
    private EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<Employee>createEmployee(@RequestBody Employee employee){
        if(employee.getEmpId().getEmpId()!=null){
        return new ResponseEntity<>(employeeService.create(employee), HttpStatus.CREATED);}
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<Employee>updateEmployee(@RequestBody Employee employee,@PathVariable Long id){
        Employee employee1=  employeeRepository.findById(id).get();
        if(employee.getName()!=null){
            employee1.setName(employee.getName());
        }
        if(employee.getRole()!=null){
            employee1.setRole(employee.getRole());
        }
        if(employee.getEmpId().getEmpId()!=null){
            employee1.getEmpId().setEmpId(employee.getEmpId().getEmpId());
        }
        return new ResponseEntity<>(employeeRepository.save(employee1),HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<List<Employee>>deleteEmployee(@PathVariable Long id){
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(employeeRepository.findAll(),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Employee>>getAllEmployee(){
        return new ResponseEntity<>(employeeRepository.findAll(),HttpStatus.OK);
    }
    @GetMapping("extract/{id}")
    public ResponseEntity getEmployeeById(@PathVariable Long id){
        Employee employee=employeeRepository.findById(id).get();
        if(employee!=null){
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("extract")
    public ResponseEntity getEmployeeByName(@RequestParam String name){
        List<Employee> employees=employeeRepository.findUserName(name);
        return new ResponseEntity(employees,HttpStatus.OK);
    }
    @GetMapping("extract/double")
    public ResponseEntity getEmployeeByNameAndRole(@RequestParam String name,@RequestParam String role){
        List<Employee>employees=employeeRepository.findAllById(name,role);
        return new ResponseEntity(employees,HttpStatus.OK);
    }

    @PostMapping("createCustomer")
    public ResponseEntity<?>createNewCustomer(@RequestBody CustomerDetails customerDetails){
        System.out.println(customerDetails.getName());
        System.out.println();
      HashMap<Object, HttpStatusCode> map=employeeService.createNewCustomer(customerDetails);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    @GetMapping("getAllCustomer")
    public ResponseEntity<?>getAllStudent(){
        return new ResponseEntity<>(customerDetailsRepository.findAll(),HttpStatus.OK);
    }

}
