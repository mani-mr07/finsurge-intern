package com.accessMultipleDatabase.AccessingMultipleDatabase.Controller;

import com.accessMultipleDatabase.AccessingMultipleDatabase.Entity.customer.Customer;
import com.accessMultipleDatabase.AccessingMultipleDatabase.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity createCustomer(@RequestBody Customer customer) {
        System.out.println(customer.getCustomerName());
        if (customer.getCustomerName() != null) {
            return new ResponseEntity(customerService.addCustomer(customer), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity("NUll CUstomer",HttpStatus.BAD_REQUEST);
        }
    }
}
