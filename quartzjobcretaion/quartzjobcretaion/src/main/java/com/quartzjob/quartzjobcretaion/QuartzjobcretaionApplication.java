package com.quartzjob.quartzjobcretaion;

import com.quartzjob.quartzjobcretaion.Entity.Employee;
import com.quartzjob.quartzjobcretaion.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzjobcretaionApplication  {
	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(QuartzjobcretaionApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		for (int i = 0; i < 10; i++) {
//			Employee employee = new Employee();
//			employee.setName("employee" + (i+1));
//			if (i % 2 == 0) {
//				employee.setRole("Developer");
//			} else {
//				employee.setRole("Manager");
//			}
//			employeeRepository.save(employee);
//
//		}
//	}
}

