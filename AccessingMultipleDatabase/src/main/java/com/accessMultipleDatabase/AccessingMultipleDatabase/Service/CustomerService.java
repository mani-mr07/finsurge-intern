package com.accessMultipleDatabase.AccessingMultipleDatabase.Service;

import com.accessMultipleDatabase.AccessingMultipleDatabase.Entity.customer.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.accessMultipleDatabase.AccessingMultipleDatabase.Repository.customer.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService (@Qualifier("customerRepository")CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Transactional(transactionManager = "customerTransactionManager")
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
