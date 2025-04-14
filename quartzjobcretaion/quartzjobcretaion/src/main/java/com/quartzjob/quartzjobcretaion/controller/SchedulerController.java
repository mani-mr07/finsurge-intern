package com.quartzjob.quartzjobcretaion.controller;

import com.quartzjob.quartzjobcretaion.Entity.Employee;
import com.quartzjob.quartzjobcretaion.Repository.EmployeeRepository;
import com.quartzjob.quartzjobcretaion.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/scheduling")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public void start(){
//        schedulerService.triggerAndPrintJob();
    }
    @PostMapping
    public ResponseEntity<Employee>insertEmployee(@RequestBody Employee employee){
     return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
    }
}
